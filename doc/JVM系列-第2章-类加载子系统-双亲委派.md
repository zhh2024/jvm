# 双亲委派

在Java中，类加载器（ClassLoader）负责动态加载Java类到Java虚拟机中。为了保证Java核心类库的类型安全，以及防止类被重复加载，Java设计了双亲委派模式。

## 类加载可能出现的问题

 **java核心类被篡改**：如果没有合适的机制来确保Java核心类库不被篡改，那么整个Java应用程序的安全性将受到威胁。

 **类被重复加载**：如果没有有效的类加载器层级和委派机制，那么同一个类可能会被多个类加载器重复加载，这将导致资源的浪费，并且可能导致程序运行时错误。

## 实现策略

双亲委派模式是一种类加载器的层级结构和委派策略，用于解决上述问题。在这个模式中，Java类加载器被组织成一个**树状结构**，每个类加载器都有一个父加载器（除了根加载器，即引导类加载器）。当一个类加载器需要加载一个类时，它首先会把这个请求委派给它的父加载器去完成，每一层的类加载器都是如此，因此所有的加载请求最终都应该传送到顶层的引导类加载器中，只有当父加载器无法完成这个加载请求（它的搜索范围中没有找到所需的类）时，子加载器才会尝试自己去加载。

### 类加载器的层级
在应用程序执行前，jvm就已经将类加载子系统(虚拟机自带的加载器)加载到内存中了。

#### 虚拟机自带的加载器

- **引导类加载器（Bootstrap ClassLoader）**：它是JVM的一部分，用来加载Java的核心类库（如rt.jar、resources.jar等）。由于引导类加载器涉及到JVM底层和本地方法，所以并没有继承自java.lang.ClassLoader，也没有父加载器。

- **扩展类加载器（Extension ClassLoader）**：由Java的Extension Mechanism实现，负责加载Java的扩展类库，默认加载JAVA_HOME/lib/ext/目录下的jar包和类。它的父加载器为引导类加载器。

- **系统类加载器（System ClassLoader）**：也叫应用类加载器（Application ClassLoader），它负责加载应用程序classpath目录下的所有jar包和类文件。它的父加载器为扩展类加载器。

#### 用户自定义类加载器
- **自定义类加载器（User-Defined ClassLoader）**:开发人员可以通过继承java.lang.ClassLoader类的方式实现自己的类加载器，以满足一些特殊的需求。自定义类加载器的父加载器可以是系统类加载器，也可以是其他自定义类加载器。

## 类加载器加载流程


### 自定义类加载器

```java
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                System.out.println("自定义加载器也无法加载。。。。。");
                throw new FileNotFoundException();
            } else {
                // defineClass和findClass搭配使用
                return defineClass(name, result, 0, result.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        throw new ClassNotFoundException(name);
    }

    // 自定义流的获取方式
    private byte[] getClassFromCustomPath(String name) {
        // 从自定义路径中加载指定类:细节略
        // 如果指定路径的字节码文件进行了加密，则需要在此方法中进行解密操作。
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 可以通过有参构造函数,传入自定义上层类加载器
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> clazz = customClassLoader.loadClass("dddd");
        try {
            // Class<?> clazz = Class.forName("com.zhang.jvmzhh.classLoad.StaticClassLoad", true, customClassLoader);
            Object obj = clazz.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 父类ClassLoader构造函数

```java
    //可以通过有参构造函数,传入自定义上层类加载器
    protected ClassLoader(ClassLoader parent) {
        this(checkCreateClassLoader(), parent);
    }

    //默认上层是系统类加载器
    protected ClassLoader() {
        this(checkCreateClassLoader(), getSystemClassLoader());
    }
    
    private ClassLoader(Void unused, ClassLoader parent) {
        this.parent = parent;
        if (ParallelLoaders.isRegistered(this.getClass())) {
            parallelLockMap = new ConcurrentHashMap<>();
            package2certs = new ConcurrentHashMap<>();
            domains =
                Collections.synchronizedSet(new HashSet<ProtectionDomain>());
            assertionLock = new Object();
        } else {
            // no finer-grained lock; lock on the classloader instance
            parallelLockMap = null;
            package2certs = new Hashtable<>();
            domains = new HashSet<>();
            assertionLock = this;
        }
    }
```

在自定义类加载器中，通过构造函数传入父加载器，使得自定义的类加载器能够遵循双亲委派模式的规则。这样，当自定义类加载器需要加载一个类时，它会首先委托给它的父加载器去加载，只有当父加载器无法加载时，才会自己尝试加载。这种机制保证了Java核心类库的安全性，并且避免了类的重复加载。


### 双亲委派代码实现

```java
protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        //递归直至父类为null
                        c = parent.loadClass(name, false);
                    } else {
                        //父类为null,引导类加载器做加载
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
```
**上述按照以下步骤进行**：

1. **检查已加载的类**：
   类加载器首先会调用`findLoadedClass(name)`方法检查该类是否已经被加载过。如果已经加载过，则直接返回该类的`Class`对象。

2. **由下往上委托给父加载器**：
   如果该类没有被加载过，类加载器会将其加载请求委托给它的父加载器。这是通过递归调用父加载器的`loadClass(name, false)`方法实现的。这个过程会一直持续到父加载器为`null`（即到达引导类加载器）或者父加载器能够成功加载该类为止。

3. **引导类加载器加载**：
   如果父加载器为`null`（即到达树形结构的顶端），类加载器会调用`findBootstrapClassOrNull(name)`方法尝试从引导类加载器中加载该类。这是因为引导类加载器是JVM的一部分，用于加载Java的核心类库。

4. **从上往下类加载器加载**：
   如果引导类加载器都无法加载该类，那么当前类加载器会调用自己的`findClass(name)`方法来尝试加载该类。返回加载结果,如果为null，递归结构从上往下执行类加载器。

5. **自定义类加载**：
   取决于自定义类加载器的上层是什么类加载器从而影响递归树的执行(可以跳过系统类加载器或者扩展类加载器,无法跳过引导类加载器),但是都得实现`findClass(name)`方法来加载

6. **记录加载时间**（可选）：
   如果类被当前类加载器成功加载，类加载器会记录加载该类所花费的时间（通过`sun.misc.PerfCounter`等性能计数器）。

7. **解析类**（可选）：
   如果调用`loadClass`方法时传入的`resolve`参数为`true`，则类加载器会调用`resolveClass(c)`方法来对加载的类进行链接（包括验证、准备和解析等步骤）。这通常是在类被首次主动使用时进行的。

7. **返回类的Class对象**：
   最后，类加载器会返回加载到的类的`Class`对象。

### 类加载器的分类
1. JVM严格来讲支持两种类型的类加载器 。分别为引导类加载器（Bootstrap ClassLoader）和自定义类加载器（User-Defined ClassLoader）,**从上述递归代码也能印证，引导类加载器（Bootstrap ClassLoader）调用`findBootstrapClassOrNull(name)`方法。自定义类加载器（User-Defined ClassLoader）调用自己的`findClass(name)`方法**。

2. 从概念上来讲，自定义类加载器一般指的是程序中由开发人员自定义的一类类加载器，但是Java虚拟机规范却没有这么定义，而是**将所有派生于抽象类ClassLoader的类加载器都划分为自定义类加载器**


## 静态代理
>自己实现代理类，一般有一个接口 IUser，接口实现类 IUserImpl，和接口代理类 IUserProxy，接口代理类构造函数中接受一个接口参数，实际使用时，将实现类传入；伪代码如下

```
interface IUser {

	void print();
}


class IUserImpl implements IUser{

	void print() {
		System.out.println("hello print");
	}
}

class IUserProxy implements IUser {

	private IUser iUser;

	public IUserProxy (IUser iUser){
		this.iUser = iUser;
	}

	void print(){
		System.out.println("proxy before hello print");
		iUser.print();
		System.out.println("proxy after hello print");
	}

}

class IUserProxyTest {


	public static void main (String args[]) {

		IUser iUser = new IUserImpl();

		IUserProxy usrProxy = new IUserProxy(iUser);

		usrProxy.print();

	}

}

```

* 对业务源码无侵入，但是需要多写一个Proxy类，导致代码代理类过于庞大
* 当接口进行变化时，代理类也要跟着一起改动

## 动态代理

### Java虚拟机加载类
1. 先了解Java虚拟机加载类的过成：加载(load)，验证(check)，准备(prepare)，解析(parse)，初始化（init） LCPPI
2. 加载阶段可以通过很多手段
2.1 通过JAR，EAR，WAR等，甚至可以自定义类型
2.2 从网络中获取 Applet
2.3 *运行时计算生成*， `java.lang.reflect.Proxy` 生成 `*$Proxy` 的代理类的二进制字节流
2.4 由其它文件生成，典型应用是JSP，即由JSP文件生成对应的Class类

### 动态代理

#### 代理代理实现的几种方式

1. https://java-source.net/open-source/bytecode-libraries
2. CGLIB
3. Javassist

#### 实现动态类思考的几个方向

* 通过实现接口的方式 -> JDK动态代理
* 通过继承类的方式 -> CGLIB动态代理

#### Java代理的几种实现方式

>静态代理和动态代理，静态代理对原代码无侵入，但是需要做很多代码冗余；动态代理分JDK动态代理和CGLIB动态代理

*JDK动态代理: *
1. 通过反射Proxy.newProxyInstance，生成代理对象  2. JDK只能代理接口，因为生成的代理类继承了Proxy，Java是单继承的

*CGLIB动态大力: *
1. CGLib 采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑，来完成动态代理的实现。
2. 实现方式实现 MethodInterceptor 接口，重写 intercept 方法，通过 Enhancer 类的回调方法来实现。
3. 但是CGLib在创建代理对象时所花费的时间却比JDK多得多，所以对于单例的对象，因为无需频繁创建对象，用CGLib合适，反之，使用JDK方式要更为合适一些。
4. 同时，由于CGLib由于是采用动态创建子类的方法，对于final方法，无法进行代理。



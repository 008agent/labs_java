// var. 335
public class Lab2 {
  public static void main(String[] args) {
  E a = new E();
  E b = new A();
  A c = new A();
  b.f5();
  c.f19();
  a.f3();
  b.f36();
  b.f29();
  c.f40();
  a.f27();
  b.f18();
  a.f23();
  c.f35();
  a.f14(a);
  a.f14(b);
  a.f14(c);
  }
}
class E {
  int f39;
  int f22;
  int f26;
  int f20;
  int f33;
  long f12;
  long f28;
  long f10;
  int[] f7 = {-1, 3, -1, -3, 2};
  int[] f21 = {0, 2, -2, -3, 3};
  int[] f37 = {3, -3, 1, -2, -3};
  static int f8;
  static int f16;
  static int f15;
  static int f30;
  static int f4;
  public E() {
    f39 = 4;
    f22 = 9;
    f26 = 7;
    f20 = 3;
    f33 = 4;
    f12 = 9L;
    f28 = 0L;
    f10 = 5L;
  }
  public void f5() {
    System.out.println("метод f5 в классе E");
    System.out.println(f33 << 2);
  }
  public void f19() {
    System.out.println("метод f19 в классе E");
    System.out.println(f37[2] + f37[2]);
  }
  public void f3() {
    System.out.println("метод f3 в классе E");
    System.out.println(f16++);
  }
  public void f36() {
    System.out.println("метод f36 в классе E");
    System.out.println(f39 >> 1);
  }
  public void f29() {
    System.out.println("метод f29 в классе E");
    System.out.println(++f20);
  }
  public void f40() {
    System.out.println("метод f40 в классе E");
    System.out.println(f15++);
  }
  public static void f27() {
    System.out.println("метод f27 в классе E");
    System.out.println(f30);
  }
  public static void f18() {
    System.out.println("метод f18 в классе E");
    System.out.println((f30 - 1));
  }
  public static void f23() {
    System.out.println("метод f23 в классе E");
    System.out.println(f4);
  }
  public static void f35() {
    System.out.println("метод f35 в классе E");
    System.out.println((f4 + 5));
  }
  public void f14(E r) {
    r.f5();
  }
  public void f14(A r) {
    r.f19();
  }
}
class A extends E {
  public A() {
    f26 = 0;
    f33 = 2;
    f12 = 8L;
    f10 = 4L;
  }
  public void f3() {
    System.out.println("метод f3 в классе A");
    System.out.println(f22);
  }
  public void f36() {
    System.out.println("метод f36 в классе A");
    System.out.println(f39 - 4);
  }
  public void f29() {
    System.out.println("метод f29 в классе A");
    System.out.println(f39--);
  }
  public static void f27() {
    System.out.println("метод f27 в классе A");
    System.out.println((f8 - 4));
  }
  public static void f18() {
    System.out.println("метод f18 в классе A");
    System.out.println(f8++);
  }
  public static void f23() {
    System.out.println("метод f23 в классе A");
    System.out.println(f16);
  }
  public static void f35() {
    System.out.println("метод f35 в классе A");
    System.out.println((f16 + 3));
  }
  public void f14(E r) {
    r.f3();
  }
  public void f14(A r) {
    r.f36();
  }
}

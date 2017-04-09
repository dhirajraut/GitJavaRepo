package Module08.exceptions.uncheckedexceptions;

public class HandlingExceptions {
  public static void main (String [] args ) {
    try {
      int array [] = {10,20,30};
      int a = 10, b = 0, c = 0;
      System.out.println ("array [2] " + array [2] );
      c = a / b ;
      System.out.println ("Division : " + c);
    } catch (ArithmeticException e) {
        System.out.println ("Math error : " + e);
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println ("Array index error : " + e);
    } catch (Exception e) {
        System.out.println ("Error : " + e);
    }
  }
}

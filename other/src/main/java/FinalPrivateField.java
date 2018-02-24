import java.lang.reflect.Field;

public class FinalPrivateField {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        final A instance = new A();

        final Field fieldA = A.class.getDeclaredField( "m_field" );
        fieldA.setAccessible( true );
        fieldA.setInt( instance, 20 );

        System.out.println( instance.getField() );
    }

    public static class A
    {
        private static final int m_field = 0;

        public A() {
        }

        public int getField() {
            return m_field;
        }
    }
}
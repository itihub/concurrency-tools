package futrue;

/**
 * run方法中无法抛出checked Exception
 */
public class RunnableCantThrewosException {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            // 无法抛出异常，必须try-catch
            //throw new Exception();
            try {
                throw new Exception();
            } catch (Exception e) {
                // 可以抛出运行时异常
                throw new RuntimeException(e);
            }
        };
        new Thread(runnable).start();
    }
}

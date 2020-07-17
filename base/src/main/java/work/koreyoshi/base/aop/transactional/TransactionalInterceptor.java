package work.koreyoshi.base.aop.transactional;

import com.jfinal.plugin.activerecord.Db;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import work.koreyoshi.base.annotation.Transactional;

/**
 * @author zhoujx
 */
public class TransactionalInterceptor  implements MethodInterceptor {

    Object result;

    /**
     * Does the transaction need to be rolled back
     * @throws Throwable 将运行时的异常抛出
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Db.tx(() -> {
            try {
                setResult(methodInvocation.proceed());
                return true;
            } catch (Throwable e) {
                setResult(e);
                Transactional tx = methodInvocation.getMethod().getAnnotation(Transactional.class);
                Class<? extends Throwable>[] rollbackFor = tx.rollbackFor();
                for (Class<? extends Throwable> aClass : rollbackFor) {
                    if (e.getClass().isAssignableFrom(aClass)) {
                        return false;
                    }
                }
                Class<? extends Throwable>[] noRollbackFor = tx.noRollbackFor();
                for (Class<? extends Throwable> aClass : noRollbackFor) {
                    if (e.getClass().isAssignableFrom(aClass)) {
                        return true;
                    }
                }
                return true;
            }
        });
        if (getResult() instanceof Throwable) {
            throw (Throwable) getResult();
        }
        return getResult();
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}

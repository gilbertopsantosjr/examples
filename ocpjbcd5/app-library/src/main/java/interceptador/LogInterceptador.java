package interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptador {

	@AroundInvoke
	public Object intercepta(InvocationContext invocationContext)
			throws Exception {
		long millis = System.currentTimeMillis();

		Object o = invocationContext.proceed();

		String nomeDaClasse = invocationContext.getTarget().getClass().getSimpleName();
		String nomeDoMetodo = invocationContext.getMethod().getName();
		
		System.out.println(nomeDaClasse+"::"+nomeDoMetodo+"  [INFO] Tempo gasto no acesso ao BD: "
				+ (System.currentTimeMillis() - millis) + "ms");

		return o;
	}

}
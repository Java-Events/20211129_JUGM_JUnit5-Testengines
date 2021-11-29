package junit.org.rapidpm.junit.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.rapidpm.junit.example.DataSource;
import org.rapidpm.junit.example.LoginService;

public class LoginServiceTest {

  public static class LoginServiceResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext)
        throws ParameterResolutionException {
      final Class<?> type = parameterContext.getParameter()
                                            .getType();
      return LoginService.class.isAssignableFrom(type);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
        throws ParameterResolutionException {
      return new LoginService(new DataSource());
    }
  }



  // make it happen :-)
  @Test
  @ExtendWith(LoginServiceResolver.class)
  void test01(LoginService service){
    Assertions.assertTrue(service.checkLogin("admin", "admin"));
  }

}

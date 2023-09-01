package karate;

import com.intuit.karate.junit5.Karate;

public class TestApiRunner {
    @Karate.Test
    Karate testApi(){
        return Karate.run("test_api").relativeTo(getClass());
    }
}

package Core;

import io.restassured.http.ContentType;

public interface ConstantesTeste {
    String APP_BASE_URL = "https://barrigarest.wcaquino.me";
    Integer APP_PORT = 443; // Para http usar porta :80, para https usar porta :443
    String APP_BASE_PATH = "";

    ContentType APP_CONTENT_TYPE = ContentType.JSON;

    Long MAX_TIMEOUT = 20000L;

}

package cloudos.main.app;

import cloudos.main.CloudOsMainBase;
import lombok.extern.slf4j.Slf4j;
import org.cobbzilla.wizard.client.ApiClientBase;
import org.cobbzilla.wizard.util.RestResponse;

import static cloudos.resources.ApiConstants.APPSTORE_ENDPOINT;
import static org.cobbzilla.util.json.JsonUtil.toJson;

@Slf4j
public class SearchAppStoreMain extends CloudOsMainBase<SearchAppStoreOptions> {

    @Override protected SearchAppStoreOptions initOptions() { return new SearchAppStoreOptions(); }

    public static void main (String[] args) throws Exception { main(SearchAppStoreMain.class, args); }

    @Override protected void run() throws Exception {

        final ApiClientBase api = getApiClient();
        final SearchAppStoreOptions options = getOptions();

        final RestResponse response;
        try {
            response = api.post(APPSTORE_ENDPOINT, toJson(options.getPage()));
            if (response.status != 200) {
                log.error("Error writing configuration. Response was: " + response);
            } else {
                log.info("Query results:\n"+response.json+"\n");
            }
        } catch (Exception e) {
            log.error("Error writing configuration: " + e, e);
        }

    }
}
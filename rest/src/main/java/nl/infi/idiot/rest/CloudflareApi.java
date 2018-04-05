package nl.infi.idiot.rest;

import com.google.common.collect.ImmutableList;
import nl.infi.idiot.core.DNSRecord;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class CloudflareApi implements DNSRestApi {

    private final String cloudflareZoneId;
    private final String cloudflareApiKey;
    private final String userEmail;

    private final String CLOUDFLARE_API_V4_BASEURL = "https://api.cloudflare.com/client/v4/";

    CloudflareApi(String cloudflareZoneId, String cloudflareApiKey, String userEmail) {
        this.cloudflareZoneId = cloudflareZoneId;
        this.cloudflareApiKey = cloudflareApiKey;
        this.userEmail = userEmail;

        this.initializeHttpClientWithDisabledCookies();
    }

    @Override
    public void addRecord(DNSRecord dnsRecord) {
        try {
            Logger.logDnsRecord(dnsRecord);
            JSONObject jsonObject = DNSRecordToJSONObjectMapper.map(dnsRecord);

            HttpResponse<JsonNode> response =
                    getBasePostRequestHeaders(baseRequestUrl()).body(jsonObject).asJson();

            Logger.logResponse(response);

        } catch (Exception e) {
            System.out.println(dnsRecord.getName() + "  " + dnsRecord.getType());
            e.printStackTrace();
        }
    }

    @Override
    public ImmutableList<DNSRecord> getRecords() {
        String url = baseRequestUrl();
        try {
            HttpResponse<JsonNode> response = getBaseGetRequestHeaders(url)
                    .asJson();

            ImmutableList<DNSRecord> records = parseRequestImmutableList(response);
            return records;

        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpRequestWithBody getBasePostRequestHeaders(String url) {
        return Unirest.post(url)
                .header("Content-Type", "application/json")
                .header("X-Auth-Key", this.cloudflareApiKey)
                .header("X-Auth-Email", this.userEmail);
    }

    private GetRequest getBaseGetRequestHeaders(String url) {
        return Unirest.get(url)
                .header("Content-Type", "application/json")
                .header("X-Auth-Key", this.cloudflareApiKey)
                .header("X-Auth-Email", this.userEmail);
    }

    private ImmutableList<DNSRecord> parseRequestImmutableList(HttpResponse<JsonNode> response) {
        JSONArray jsonArray = response.getBody().getObject().getJSONArray("result");
        List<JSONObject> list = parseToJSONObjects(jsonArray);
        return list.stream().map(json -> new DNSRecord.Builder()
                .setName(json.getString("name"))
                .setType(json.getString("type"))
                .setTtl(json.getInt("ttl"))
                .setValue(json.getString("content"))
                .build())
                .collect(ImmutableList.toImmutableList());
    }

    private List<JSONObject> parseToJSONObjects(JSONArray jsonArray) {
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getJSONObject(i));
        }
        return list;
    }

    private String baseRequestUrl() {
        return String.join(CLOUDFLARE_API_V4_BASEURL, String.format("zones/%s/dns_records", this.cloudflareZoneId));
    }


    private void initializeHttpClientWithDisabledCookies() {
        HttpClient httpClient = HttpClients.custom()
                .disableCookieManagement()
                .build();
        Unirest.setHttpClient(httpClient);
    }

    private static class DNSRecordToJSONObjectMapper {
        static JSONObject map(DNSRecord dnsRecord) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("proxied", false);
            jsonObject.put("type", dnsRecord.getType());
            jsonObject.put("name", dnsRecord.getName());
            jsonObject.put("content", dnsRecord.getValue());

            if (dnsRecord.getPreference().isPresent()) {
                jsonObject.put("priority", dnsRecord.getPreference());
            }

            jsonObject.put("ttl", dnsRecord.getTtl());

            return jsonObject;
        }
    }

    private static class Logger {

        static void logDnsRecord(DNSRecord dnsRecord) {
            System.out.println(String.format("Pushing record of type %s with value %s ...", dnsRecord.getType(), dnsRecord.getValue()));
        }

        static void logResponse(HttpResponse<JsonNode> response) {
            System.out.println(String.format("Response -> %s %s -> %s \n", response.getStatus(), response.getStatusText(), response.getBody()));
        }
    }

}

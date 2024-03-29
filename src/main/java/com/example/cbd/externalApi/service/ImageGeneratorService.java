package com.example.cbd.externalApi.service;

/*
    Old External API -> OpenAI Image Generation

@Service
public class ImageGeneratorService implements ImageGeneratorServiceMethods {

    private final String BASE_URL = "https://api.openai.com/v1";
    private final String ENDPOINT_URL = "/images/generations";
    private final String API_TOKEN;

    //256x256, 512x512, 1024x1024
    private final String IMAGE_SIZE_SMALL = "256x256";
    private final String IMAGE_SIZE_MEDIUM = "512x512";
    private final String IMAGE_SIZE_LARGE = "1024x1024";

    private final String RANDOM_PROMPT = "Please generate a random image";

    private final WebClient client;

    private static final Logger log = LoggerFactory.getLogger(ImageGeneratorService.class);

    public ImageGeneratorService() {
        API_TOKEN = System.getenv("TOKEN");
        client = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_TOKEN)
                .build();
    }

    @Override
    public String getImageByPrompt(String prompt) throws IOException, ExternalApiException {
        URL url = getImageUrl(prompt);
        //processImage(url);
        log.info("Image Url: " + url + ", Prompt: " + prompt);
        return url.toString();
    }

    @Override
    public String getRandomImage() throws IOException, ExternalApiException {
        URL url = getImageUrl(RANDOM_PROMPT);
        //processImage(url);
        System.out.println(url);
        return url.toString();
    }

    private String processImage(URL url) throws IOException {
        BufferedImage image = ImageIO.read(url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.close();

        //todo what to do with images?
        return "";
    }

    private URL getImageUrl(String prompt) throws ExternalApiException {
        try {
            var response = apiRequest(prompt);
            if (response != null) {
                return new URL(response);
            }
            throw new ExternalApiException("The API was not able to create an Image out of the prompt");
        } catch (MalformedURLException e) {
            throw new ExternalApiException("There was a problem generating the image URL. " + e.getMessage());
        }
    }



    private String apiRequest(String prompt) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);
        requestBody.put("n", 1);
        requestBody.put("size", IMAGE_SIZE_LARGE);
        try {
            return client.post()
                    .uri(ENDPOINT_URL)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            System.out.println(e.getStatusCode().value());
            System.out.println(e.getResponseBodyAsString());
            return "";
        }

    }

}*/

private class ContactModel {
    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


private interface FormsparkApi {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("your-form-id")
    Call<BasicResponseModel> contact(@Body ContactModel contactModel);

}

public class FormsparkService {

    private void submit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://submit-form.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        RetrofitApi retrofitApi = retrofit.create(FormsparkApi.class);

        ContactModel contactModel = new ContactModel();
        contactModel.setName("your-name");
        contactModel.setMessage("your-message");

        call = retrofitApi.contactUs(contactModel);

        call.enqueue(new Callback<BasicResponseModel>() {
            @Override
            public void onResponse(Call<BasicResponseModel> call, Response<BasicResponseModel> response) {
                progressBar.setVisibility(View.GONE);
                runningService = false;
                if (response.isSuccessful()) {
                    // Request succesful

                } else {
                    // request not succesful
                }

            }

            @Override
            public void onFailure(Call<BasicResponseModel> call, Throwable t) {
                if (call.isCanceled()) {
                    // Request canceled
                } else {
                    // Request failed
                }
            }
        });
    }

}
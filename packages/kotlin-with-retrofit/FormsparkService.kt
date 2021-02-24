data class ContactResponse(val name: String, val message: String)

interface ApiService {

    @POST("your-form-id")
    fun contact(@Body data: HashMap<String, Any>): Single<ContactResponse>

}

class FormsparkService {

    companion object {
        private var instance: ServiceBuilder? = null
        private var retrofit: ApiService? = null
        private var context: Context? = null

        private const val baseURL = "https://submit-form.com/"

        fun initialize(context: Context) {
            if (instance == null) {
                instance = ServiceBuilder()
                Companion.context = context
                instance!!.initializeRetro()
            }
        }

        fun get(): ApiService {
            return retrofit
                    ?: throw IllegalStateException("FormsparkService must be initialized first.")
        }
    }

    private fun initializeRetro() {
        retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(getOkHttpClient())
                .build()
                .create(ApiService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient
                .Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val requestBuilder: Request.Builder = chain.request().newBuilder()
                    requestBuilder.header("Content-Type", "application/json")
                    requestBuilder.header("Accept", "application/json")
                    chain.proceed(requestBuilder.build())
                }.build()
    }

}

// Then call: FormsparkService.get().contact(data)
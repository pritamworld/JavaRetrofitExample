import models.SOAnswersResponse;
import models.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JavaRetrofitExample {
    public static void main(String... args)
    {
        loadAnswers();
    }

    public static void loadAnswers() {
        SOService mService = ApiUtils.getSOService();
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                if(response.isSuccessful()) {
                    System.out.println("Success");
                    SOAnswersResponse soAnswersResponse = response.body();
                    System.out.println(soAnswersResponse.toString());
                }else {
                    int statusCode  = response.code();

                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {

                System.out.println("Fail");
            }
        });
    }

}

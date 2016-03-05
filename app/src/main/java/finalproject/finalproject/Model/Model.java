package finalproject.finalproject.Model;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Anna on 05-Mar-16.
 */
public class Model {
    private final static Model instance = new Model();
    private Context context = null;

    ModelSql local = new ModelSql();

    private Model() {
    }

    public static Model getInstance() {
        return instance;
    }

    public void init(Context context) {
        if (this.context == null) {
            this.context = context;
            local.init(context);
        }
    }

    public interface GetCheckinsListener {
        public void onResult(List<Checkin> checkins);
    }

    public interface GetCheckinListener {
        public void onResult(Checkin checkin);
    }

    public interface SimpleSuccessListener {
        public void onResult(boolean result);
    }

    // Local database

    public Checkin getLocalCheckin(String type, int time) {
        return local.getCheckinByTypeAndTime(type, time);
    }

    public long addLocalCheckin(Checkin checkin) {
        return local.addOrUpdateCheckin(checkin);
    }

    public int deleteLocalCheckin(String type, int time) {
        return local.deleteCheckin(type,time);
    }

    public void getLocalCheckinAsync(final GetCheckinListener listener, final String type,final int time) {
        class GetCheckinAsyncTask extends AsyncTask<String, String, Checkin> {
            @Override
            protected Checkin doInBackground(String... params) {
                return local.getCheckinByTypeAndTime(type,time);
            }

            @Override
            protected void onPostExecute(Checkin note) {
                super.onPostExecute(note);
                listener.onResult(note);
            }
        }

        GetCheckinAsyncTask task = new GetCheckinAsyncTask();
        task.execute();
    }

    public void addLocalCheckinAsync(final SimpleSuccessListener listener, final Checkin checkin) {
        class AddCheckinAsyncTask extends AsyncTask<String, String, Long> {
            @Override
            protected Long doInBackground(String... params) {
                return local.addOrUpdateCheckin(checkin);
            }

            @Override
            protected void onPostExecute(Long id) {
                super.onPostExecute(id);
                listener.onResult(id > -1);
            }
        }

        AddCheckinAsyncTask task = new AddCheckinAsyncTask();
        task.execute();
    }

    public void deleteLocalCheckinAsync(final SimpleSuccessListener listener,final String type,final int time) {
        class DeleteCheckinAsyncTask extends AsyncTask<String, String, Integer> {
            @Override
            protected Integer doInBackground(String... params) {
                return local.deleteCheckin(type,time);
            }

            @Override
            protected void onPostExecute(Integer id) {
                super.onPostExecute(id);
                listener.onResult(id > -1);
            }
        }

        DeleteCheckinAsyncTask task = new DeleteCheckinAsyncTask();
        task.execute();
    }

    public void getAllLocalCheckinsAsync(final GetCheckinsListener listener) {
        class GetCheckinsAsyncTask extends AsyncTask<String, String, List<Checkin>> {
            @Override
            protected List<Checkin> doInBackground(String... params) {
                return local.getAllCheckins();
            }

            @Override
            protected void onPostExecute(List<Checkin> checkins) {
                super.onPostExecute(checkins);
                listener.onResult(checkins);
            }
        }

        GetCheckinsAsyncTask task = new GetCheckinsAsyncTask();
        task.execute();
    }
}

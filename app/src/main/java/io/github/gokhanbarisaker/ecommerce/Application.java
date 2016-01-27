package io.github.gokhanbarisaker.ecommerce;

import android.content.Context;

import com.gokhanbarisaker.osapis.utility.DeviceUtilities;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by gokhanbarisaker on 11/20/15.
 */
public class Application extends android.app.Application {

    public static final Executor EXECUTOR_BACKGROUND = DeviceUtilities.getCurrentDevice().getBackgroundThreadExecutor();
    public static final Scheduler SCHEDULER_BACKGROUND = Schedulers.from(EXECUTOR_BACKGROUND);
    public static final OkHttpClient client = new OkHttpClient();
    public static Context context;
    public static Picasso picasso;
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(120, TimeUnit.SECONDS);
        client.setWriteTimeout(120, TimeUnit.SECONDS);
        client.setCache(getDiskCache("httpclient"));

        picasso = new Picasso.Builder(this)
                .downloader(new OkHttpDownloader(client))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://private-524e0-ecommerce4.apiary-mock.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    // TODO: Convert to fluent API Just For Fun :)
    private Cache getDiskCache(final String label) {
        int size = 10 * 1024 * 1024;    // 10 mb.
        File directory = getDiskCacheDirectory(label);
        return (directory == null) ? (null) : (new Cache(directory, size));
    }

    // TODO: Check ContextCompat to access cache directories
    private File getDiskCacheDirectory(final String label) {
        File rootDirectory = getExternalCacheDir();

        // If external storage failed to provide cache
        if (rootDirectory == null) {
            // Fallback to internal storage
            rootDirectory = getCacheDir();
        }

        return (rootDirectory == null) ? (null) : (new File(rootDirectory, label));
    }
}

package eu.ase.ro.seminars.network;

import java.util.List;
import java.util.concurrent.Callable;

import eu.ase.ro.seminars.utils.Movie;

public class XmlCallableTask implements Callable<List<Movie>> {
    private final HttpConnection httpConnection;

    public XmlCallableTask(HttpConnection httpConnection) {

        this.httpConnection = httpConnection;
    }

    @Override
    public List<Movie> call() throws Exception {
        return httpConnection.getXmlContent();
    }
}

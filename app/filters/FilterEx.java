package filters;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.Function;

import javax.inject.Inject;
import javax.inject.Singleton;

import akka.stream.Materializer;
import play.mvc.Filter;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

@Singleton
public class FilterEx extends Filter {
	
	private final Executor exec;
	
	@Inject
	public FilterEx(Materializer mat, Executor exec) {
		super(mat);
		this.exec = exec;
	}

	@Override
	public CompletionStage<Result> apply(Function<RequestHeader, CompletionStage<Result>> next, RequestHeader requestHeader) {
		return next.apply(requestHeader).thenApplyAsync(result -> result.withHeader("New_Header", "Baaa"), exec);
	}
}

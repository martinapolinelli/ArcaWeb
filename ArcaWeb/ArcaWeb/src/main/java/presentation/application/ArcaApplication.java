package presentation.application;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import presentation.provider.ProviderFilter;
import presentation.rest.ArcaRest;

@ApplicationPath("rest")
public class ArcaApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<>();
		s.add(ArcaRest.class);
		s.add(ProviderFilter.class);		
		return s;
	}
}
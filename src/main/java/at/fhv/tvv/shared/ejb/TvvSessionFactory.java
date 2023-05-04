package at.fhv.tvv.shared.ejb;

import javax.ejb.Remote;

@Remote
public interface TvvSessionFactory {
    TvvSession createSession();
}

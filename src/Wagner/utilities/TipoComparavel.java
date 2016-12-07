package Wagner.utilities;

public interface TipoComparavel<T> {
	public boolean greaterThan(T a);
	public boolean lesserThan(T a);
	public boolean equalTo(T a);
}

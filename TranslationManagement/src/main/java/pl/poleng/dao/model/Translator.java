package pl.poleng.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "person_id")
@Table(name = "translators")
public class Translator extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4876090342477074278L;

	private String symbol;
	private String symbolProof;
	
	
	public String getSymbolProof() {
		return symbolProof;
	}

	public void setSymbolProof(String symbolProof) {
		this.symbolProof = symbolProof;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Translator other = (Translator) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Translator [symbol=" + symbol + "]";
	}

	

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}

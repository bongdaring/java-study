package prob03;

import java.util.Objects;

public class Money {
	private int amount = 0;
	
	public Money(int amount) {
		this.amount = amount;
	}

	public Object add(Money money) {
		return new Money(this.amount + money.amount);
	}

	public Object minus(Money money) {
		return new Money(this.amount - money.amount);
	}

	public Object multiply(Money money) {
		return new Money(this.amount * money.amount);
	}

	public Object devide(Money money) {
		return new Money(this.amount / money.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return amount == other.amount;
	}
	
	/* 코드 작성 */
}

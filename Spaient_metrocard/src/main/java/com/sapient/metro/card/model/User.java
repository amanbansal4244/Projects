package com.sapient.metro.card.model;

public class User {
	private long userId;
	private String name;

	public long getUserId() {
		return userId;
	}

	public void setUserId(final long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (userId != other.userId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + "]";
	}

}

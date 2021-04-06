package com.techelevator.application.model;

import java.time.LocalDate;

public class Matches {
		
		private Long matchId;
		private LocalDate startDate;
		private LocalDate startTime;
		/**
		 * @return the matchId
		 */
		public Long getMatchId() {
			return matchId;
		}
		/**
		 * @param matchId the matchId to set
		 */
		public void setMatchId(Long matchId) {
			this.matchId = matchId;
		}
		/**
		 * @return the startDate
		 */
		public LocalDate getStartDate() {
			return startDate;
		}
		/**
		 * @param startDate the startDate to set
		 */
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		/**
		 * @return the startTime
		 */
		public LocalDate getStartTime() {
			return startTime;
		}
		/**
		 * @param startTime the startTime to set
		 */
		public void setStartTime(LocalDate startTime) {
			this.startTime = startTime;
		}
		@Override
		public String toString() {
			return "Matches [matchId=" + matchId + ", startDate=" + startDate + ", startTime=" + startTime + "]";
		}
	}

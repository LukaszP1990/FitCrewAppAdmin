package com.fitcrew.FitCrewAppAdmin.enums;

public enum AdminErrorMessageType {

	NO_ADMIN_FOUND("No admin found"),
	NO_ADMIN_SAVED("Admin save failed"),
	NO_CLIENTS_FOUND("No clients found"),
	NO_CLIENT_FOUND("No client found"),
	NO_CLIENT_DELETED("No client deleted"),
	NO_CLIENT_UPDATED("No client updated"),
	NO_TRAINERS_FOUND("No trainers found"),
	NO_TRAINER_FOUND("No trainer found"),
	NO_TRAINER_DELETED("No trainer deleted"),
	NO_TRAINER_UPADTED("No trainer updated");

	AdminErrorMessageType(String message) {
	}
}

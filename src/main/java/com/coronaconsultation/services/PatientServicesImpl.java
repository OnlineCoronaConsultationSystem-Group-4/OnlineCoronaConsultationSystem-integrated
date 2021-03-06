package com.coronaconsultation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.coronaconsultation.entities.DischargedPatients;
import com.coronaconsultation.entities.DoctorReport;
import com.coronaconsultation.entities.Feedback;
import com.coronaconsultation.entities.Patient;
import com.coronaconsultation.entities.PatientReport;
import com.coronaconsultation.entities.PatientRequests;
import com.coronaconsultation.entities.Services;
import com.coronaconsultation.entities.Treatment;
import com.coronaconsultation.repository.DischargePatientsRepository;
import com.coronaconsultation.repository.FeedbackRepository;
import com.coronaconsultation.repository.PatientRepository;
import com.coronaconsultation.repository.PatientRequestRepository;
import com.coronaconsultation.repository.ServicesRepository;
import com.coronaconsultation.repository.TreatmentRepository;

@Service
public class PatientServicesImpl implements IPatientServices {
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	ServicesRepository servicesRepository;
	@Autowired
	DischargePatientsRepository dischargePatientsRepository;
	@Autowired
	TreatmentRepository treatmentRepository;
	@Autowired
	FeedbackRepository feedbackRepository;
	@Autowired
	PatientRequestServicesImpl patientRequestServicesImpl;
	@Autowired
	PatientRequestRepository patientRequestRepository;

	@Override
	public boolean AcceptPatient(PatientRequests patientRequests, Services services) {
		// TODO Auto-generated method stub
		Patient patient = new Patient();
		if (patientRequests != null) {
			// patient.setPatientid(patientRequests.getPatientid());
			patient.setEmail(patientRequests.getEmail());
			patient.setName(patientRequests.getName());
			patient.setGender(patientRequests.getGender());
			patient.setLocation(patientRequests.getLocation());
			patient.setMobile(patientRequests.getMobile());
			//patient.setService(services);
			services.setPatient(patient);
			patient.setPatientId(patientRequests.getPatientid());
			patientRepository.save(patient);
			
			patientRequestRepository.deleteById(patientRequests.getPatientid());
			servicesRepository.save(services);
			//patient.setDoctorReport(doctorReport);
			//patient.setFeedback(feedback);
			//patient.setPatientReport(patientReport);
			//patient.setTreatement(null);
			
			// services.setPatient(patient);
			System.out.println(patient);
			
			return true;
		}

		return false;
	}

	@Override
	public boolean DischargePatient(int id, String dischargeSummary) {
		Patient patient = patientRepository.findById(id).get();
		DischargedPatients dischargedPatients = new DischargedPatients();
		if (patient != null) {
			dischargedPatients.setPatient(patient);
			dischargedPatients.setDischargeSummary(dischargeSummary);
			dischargedPatients.setDoctor(patient.getDoctorReport().getDoctor());
			return true;
		}
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean AddTreatment(int id, List<Treatment> treatment) {
		// TODO Auto-generated method stub
		Patient patient = patientRepository.findById(id).get();
		if (patient != null) {
			patient.setTreatement(treatment);
			return true;
		}

		return false;
	}

	@Override
	public boolean GiveFeedback(int id, Feedback feedback) {
		// TODO Auto-generated method stub
		Patient patient = patientRepository.findById(id).get();
		if (patient != null) {
			feedbackRepository.save(feedback);
			return true;
		}
		return false;
	}

	@Override
	public boolean AddDoctorReport(int id, DoctorReport doctorReport) {
		// TODO Auto-generated method stub
		Patient patient = patientRepository.findById(id).get();
		if (patient != null) {
			patient.setDoctorReport(doctorReport);
			return true;
		}
		return false;
	}

	@Override
	public boolean AddPatientReport(int id, PatientReport patientReport) {
		// TODO Auto-generated method stub
		Patient patient = patientRepository.findById(id).get();
		if (patient != null) {
			patient.setPatientReport(patientReport);
			return true;
		}
		return false;
	}

	@Override
	public PatientReport ViewPatientReport(int id) {
		// TODO Auto-generated method stub
		Patient patient = patientRepository.findById(id).get();
		if (patient != null) {
			return patient.getPatientReport();
		}
		return null;
	}

	@Override
	public DoctorReport ViewDoctorReport(int id) {
		// TODO Auto-generated method stub
		Patient patient = patientRepository.findById(id).get();
		if (patient != null) {
			return patient.getDoctorReport();
		}
		return null;
	}

	@Override

	public List<Treatment> ViewTreatment(int id) {
		// TODO Auto-generated method stub
		Patient patient = patientRepository.findById(id).get();
		if(patient!= null) {
			return patient.getTreatement();
		}
		return null;
	}

	@Override
	public String ViewDischargeSummary(int id) {
		// TODO Auto-generated method stub
		Patient patient = patientRepository.findById(id).get();
		if (patient != null) {
			String summary = dischargePatientsRepository.findById(id).get().getDischargeSummary();
			String message = summary + "\nBy " + dischargePatientsRepository.findById(id).get().getDoctor().getName();
			return message;
		}
		return null;
	}
	

}

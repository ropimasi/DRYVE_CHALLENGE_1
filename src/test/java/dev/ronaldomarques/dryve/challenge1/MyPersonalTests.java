package dev.ronaldomarques.dryve.challenge1;


import java.util.UUID;
import dev.ronaldomarques.dryve.challenge1.domain.model.EStatus;
import dev.ronaldomarques.dryve.challenge1.domain.model.entity.MotorVehicleEntity;
import dev.ronaldomarques.myutility.debugger.DP;



public final class MyPersonalTests {
	
	public static void main(String[] args) {
		
		MotorVehicleEntity mv = new MotorVehicleEntity();
		
		System.out.println(mv.getVeAdvStatus());
		mv.setVeAdvStatus(EStatus.ACTIVE);
		System.out.println(mv.getVeAdvStatus());
		mv.setVeAdvStatus(EStatus.INACTIVE);
		System.out.println(mv.getVeAdvStatus());
		
		System.out.println(UUID.fromString("ca43ec74-5bb0-4288-ab11-5df094ca4dc4").version());
		// versão-4 ...-(4)288-... = correto.
		
		DP.pd("teste 1");
		
	}
	
}

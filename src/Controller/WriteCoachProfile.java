package Controller;

import Entity.Trainer;

import java.io.IOException;

public class WriteCoachProfile implements ProfileWriter {
    public boolean writeCoachProfile(Trainer trainer) throws IOException {

        String str = "coachProfile";

        String info = trainer.getTrainerID() + "#" + trainer.getGender() + "#" + trainer.getHeight()
                + "#" + trainer.getWeight() + "#" + trainer.getTel() +"#" + trainer.getName() ;

        FileWriter(info, str, trainer.getTrainerID());
        return true;
    }


}

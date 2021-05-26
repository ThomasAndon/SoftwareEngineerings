package Controller;

import Entity.Trainer;

import java.io.IOException;

public class SetCoachProfile{

    public Trainer setCoachProfile(Trainer trainer)throws IOException {
        String[] info=new profilePath().readFile("coachProfile/", trainer.getTrainerID());

        trainer.setGender(info[1]);
        trainer.setHeight(Double.parseDouble(info[2]));
        trainer.setWeight(Double.parseDouble(info[3]));
        trainer.setTel(Integer.parseInt(info[4]));
        trainer.setName(info[5]);

        return trainer;
    }
}

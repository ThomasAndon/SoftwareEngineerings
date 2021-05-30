package Controller;

import Entity.Trainer;

import java.io.IOException;

public class WriteCoachProfile {
    /**
     * Write the coach information into a file
     * @param trainer
     * @return
     * @throws IOException
     */
    public boolean writeCoachProfile(Trainer trainer) throws IOException {

        String str = "coachProfile";
        String info = trainer.getTrainerID() + "#" + trainer.getGender() + "#" + trainer.getHeight()
                + "#" + trainer.getWeight() + "#" + trainer.getTel() +"#" + trainer.getName() + "#" + trainer.getIntro();

        new ProfileWriter().FileWriter(info, str, trainer.getTrainerID());
        return true;
    }


}

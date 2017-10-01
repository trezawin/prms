package sg.edu.nus.iss.phoenix.scheduleprogram.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T15:12:53")
@StaticMetamodel(ProgramSlot.class)
public class ProgramSlot_ { 

    public static volatile SingularAttribute<ProgramSlot, Date> duration;
    public static volatile SingularAttribute<ProgramSlot, String> programName;
    public static volatile SingularAttribute<ProgramSlot, Date> dateOfProgram;
    public static volatile SingularAttribute<ProgramSlot, Integer> id;

}
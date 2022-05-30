package yu.rs.co.edfeahs.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity @DiscriminatorValue("ROLE_ADMIN") // Jakarta
public class Administrator extends Person { }

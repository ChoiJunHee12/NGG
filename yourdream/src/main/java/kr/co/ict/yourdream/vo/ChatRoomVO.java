package kr.co.ict.yourdream.vo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TBCHATROOM")
public class ChatRoomVO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chtno")
    @SequenceGenerator(name = "seq_chtno", sequenceName = "seq_chtno", allocationSize = 1)
    private Integer chtno;
    @Column
    private int memno;
    @Column(length = 20)
    private String cnsno;
    @Column
    private String chatname;
    @Column
    private Date credt;
    @Column
    private Date upddt;

}

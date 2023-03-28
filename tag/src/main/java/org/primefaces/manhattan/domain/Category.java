
package org.primefaces.manhattan.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class Category implements Serializable {
    
      private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

   
}

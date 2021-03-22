package util;

import java.util.Scanner;

/**
 * Muestra un menú con las siguientes opciones:
 * 
 * 1.-Introducir (por teclado) una fecha pidiendo por teclado año, mes y día en formato dd/mm/aaaa.
 * Si no se introduce correctamente se devuelve un mensaje de error. Usa una función booleana para 
 * validar la fecha.
 * 
 * 2.-Añadir días a la fecha. Pide un número de días para sumar a la fecha introducida previamente 
 * y actualiza su valor. Si el número es negativo restará los días. Esta opción sólo podrá realizarse 
 * si hay una fecha introducida (se ha ejecutado la opción anterior), si no la hay mostrará un mensaje 
 * de error.
 * 
 * 3.-Añadir meses a la fecha. El mismo procedimiento que la opción anterior.
 * 
 * 4.-Añadir años a la fecha. El mismo procedimiento que la opción 2.
 * 
 * 5.-Comparar la fecha introducida con otra. Pide una fecha al usuario en formato dd/mm/aaaa (válida, 
 * si no lo es da error) y la comparará con la que tenemos guardada, posteriormente mostrará si esta 
 * fecha es anterior, igual o posterior a la que tenemos almacenada y el número de días comprendido 
 * entre las dos fechas.
 * 
 * 6.-Mostrar la fecha en formato largo (ejemplo: "lunes, 1 de febrero de 2021").
 * 
 * 7.-Terminar.
 * 
 * Consideraciones a tener en cuenta:
 * -El menú lo hacemos con una clase a la que llamaremos Menú, esa clase permitirá ir añadiendo opciones 
 * y escoger alguna opción.
 * -Las fechas las manejaremos con la clase LocalDate.
 * 
 * @author Rubén Ramírez Rivera
 *
 */
public class Menu {
  
  private String title = "";
  private String[] options;
  private int totalOptions;
  
  private static Scanner s = new Scanner(System.in);
  
  //Constructor
  /**
   * Menu method. Menu's class constructor
   * 
   * @param options
   */
  public Menu(String title, String ... options) {
    this.title += title;
    this.options = new String[options.length];
    this.totalOptions = options.length;
    
    for (int i = 0; i < options.length; i++) {
      this.options[i] = options[i];
    }
  }
    
  //Methods
  public int pickOption() {
    int option;
    
    do {
      try {
        do {
          System.out.println(this.showMenu());
          option = Integer.parseInt(s.nextLine());
          
        } while (option <= 0 || option > this.totalOptions);
        return option;
      } catch (NumberFormatException e) {
        System.out.println("Please give us a correct number");
      }
    } while (true);        
    
  }
  
  /**
   * Menu we're going to use to execute the exam
   * 
   * @return option the user wants to execute
   * 
   */
  public String showMenu() {
      
    String menu = "";    

    menu += ("\n\t" + this.title + "\n\n");
    
    for (int i = 0; i < this.options.length; i++) {
      menu += (i+1) + ".- " + this.options[i] + ".\n";
    }
    
    menu += "\nSelect your option: ";
    
    return menu;
  }
}

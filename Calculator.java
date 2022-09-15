import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//TODO see what you can do to improve it



/*
    what I changed
    - made it go right to left (there should be no visiable bug)
    - made it where the (-) sign is in the right spot
    - change where you can't put in multiple decimal
    - made it where when you open the cal you see the number zero just like micrsoft cal
    - made the boarder clear
    - made it where the display text is going to display the equation
    - fix the bug where when you do multiple symbol it would cause a bug in the terminal
    - added when you push the equal button first it will be equal to the number that user first input
    - if user input a number and a operator than user can constantly use that equation and the number
*/

public class Calculator implements ActionListener{
    
    JFrame frame; // how people will visable see the button or text.
    JTextField textfield; // user are enter number
    JTextField displayfield; // this is user equation
    JButton[] numberButtons = new JButton[10]; // make the button which is 0-9
    JButton[] functionButtons = new JButton[9]; // this will hold add, subtract
    JButton addButton,subButton,mulButton,divButton;//function button
    JButton decButton, equButton,delButton,clrButton,negButton;
    JPanel panel;

    Font myFont = new Font("Ink Free",Font.BOLD,30); // font of the calulator
    Font displayFont = new Font("Ink Free",Font.BOLD,15); // font of the calulator

    double num1=0, num2=0, num3=0, result = 0;
    char operator = '\0',operator2='\0';


    Calculator(){// construtor
        frame = new JFrame("Calculator"); // title of the app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to exit
        frame.setSize(420,550); // size of the calulator
        frame.setLayout(null);

        textfield = new JTextField("0"); // making a visable user input
     
        textfield.setBounds(50,30,300,50); // location and the height of user input
        textfield.setFont(myFont); // setting the font
        textfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());// empty boarder so no boarder
        textfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        displayfield = new JTextField("");
        displayfield.setBounds(50,10,300,20);
        displayfield.setFont(displayFont);
        displayfield.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        displayfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        displayfield.setEditable(false);
        textfield.setEditable(false); // this won't let user to use the box but user can use the button
        // text button
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("C");
        negButton = new JButton("(-)");
        // adding it to the array
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i =0; i<9; i++)// making everything to have the same font
        {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }
        for (int i =0; i<10; i++)// making everything the same font for the number
        {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(true);
        }
        // setting up the button size for the neg and del and clear
        negButton.setBounds(50,430,100,50);
        delButton.setBounds(150,430,100,50);
        clrButton.setBounds(250,430,100,50);

        //making the panel
        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10));
        
        // putting the number and the symbol in the layout
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        //user can visably see this
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield); // adding it to frame so this wway it can be visiable
        frame.add(displayfield);
        frame.setVisible(true); // this will allow me to see it

    }
    	public static void main(String[] args) {
		
		Calculator calc = new Calculator();
	}

    @Override
    public void actionPerformed(ActionEvent e){ // functionality
        textfield.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT); // making it go left first to get -0

        displayfield.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        String str_check = textfield.getText(); // checking
        String display_temp = displayfield.getText();


        for(int i =0; i < 10;i++){
            if(e.getSource() == numberButtons[i]){

                if(str_check.isEmpty() == false && str_check.charAt(0)  == '0' || display_temp.isEmpty() == false && display_temp.charAt(display_temp.length()-1) == '='){// if tthe number from the beginning is 0 than i want to get rid of it else 
                    textfield.setText("");
                    textfield.setText(textfield.getText().concat(String.valueOf(i)));
                    displayfield.setText("");
                    num1=0;
                    num2=0;
                    num3=0;
                    operator2 = '\0';
                    operator = '\0';
                    result = 0;
                }
                else{
                    textfield.setText(textfield.getText().concat(String.valueOf(i)));
                }
                
            }
        }
        if(e.getSource() == decButton){
            // checking to see if the text field have a decimal in the field
            boolean no_dec = true;
            for(int i =0; i < str_check.length(); i++){
                if(str_check.charAt(i) == '.'){// if the text field does have a decimal will make no decimal (variable) false 
                    no_dec = false;
                }
            }


            if(no_dec){ // if it false it won't add anything to the textfield if it's true than it will add a decimal
                textfield.setText(textfield.getText().concat("."));
            }
        }
        if(e.getSource() == addButton){
            if(str_check.isEmpty() == false){
                num1 = Double.parseDouble(textfield.getText()); 
                switch(operator){
                    case '+':                
                    result = result + num1;
                    break;
                    case'-':
                    result = result - num1;
                    break;
                    case'*':
                    result = result * num1;
                    break;
                    case'/':
                    result = result / num1;
                    break;
                    }
                operator ='+';


                if(display_temp.isEmpty() == true){
                    displayfield.setText(num1 + " " + operator);
                    result = num1;
                    textfield.setText(""); 
                }
                else{
                    displayfield.setText(result + " " + operator);
                    textfield.setText(""); 
                }
            }

        }
        if(e.getSource() == subButton){
            if(str_check.isEmpty() == false){
                num1 = Double.parseDouble(textfield.getText());
                
                
                switch(operator){
                    case '+':                
                    result = result + num1;
                    break;
                    case'-':
                    result = result - num1;
                    break;
                    case'*':
                    result = result * num1;
                    break;
                    case'/':
                    result = result / num1;
                    break;
                    }
                operator ='-';


                if(display_temp.isEmpty() == true){
                    displayfield.setText(num1 + " " + operator);
                    result = num1;
                    textfield.setText(""); 
                }
                else{
                    displayfield.setText(result + " " + operator);
                    textfield.setText(""); 
                }

            }

        }
        if(e.getSource() == mulButton){
            if(str_check.isEmpty() == false){
                num1 = Double.parseDouble(textfield.getText());


                switch(operator){
                case '+':                
                result = result + num1;
                break;
                case'-':
                result = result - num1;
                break;
                case'*':
                result = result * num1;
                break;
                case'/':
                result = result / num1;
                break;
                }

                 operator = '*';
                if(display_temp.isEmpty() == true){
                    displayfield.setText(num1 + " " + operator);
                    result = num1;
                    textfield.setText(""); 
                }
                else{
                    displayfield.setText(result + " " + operator);
                    textfield.setText(""); 
                }
                
            }

        }
        if(e.getSource() == divButton){
            if(str_check.isEmpty() == false){
                num1 = Double.parseDouble(textfield.getText()); 
                switch(operator){
                    case '+':                
                    result = result + num1;
                    break;
                    case'-':
                    result = result - num1;
                    break;
                    case'*':
                    result = result * num1;
                    break;
                    case'/':
                    result = result / num1;
                    break;
                    }
                operator ='/';


                if(display_temp.isEmpty() == true){
                    displayfield.setText(num1 + " " + operator);
                    result = num1;
                    textfield.setText(""); 
                }
                else{
                    displayfield.setText(result + " " + operator);
                    textfield.setText(""); 
                }
            }

        }

        if(e.getSource() == equButton){
            if(str_check.isEmpty() == false){
            num2 = Double.parseDouble(textfield.getText());
            num1 = result;
            }
            else
            num2 = num1;
            
            switch(operator){
                case '+':                
                result = num1+num2;
                displayfield.setText(displayfield.getText() + " " + num2 + " =");
                operator2 = '+';
                operator = '=';
                num3 = num2;
                break;
                case'-':
                result = num1 - num2;
                displayfield.setText(displayfield.getText() + " " + num2 + " =");
                operator2 = '-';
                operator = '=';
                num3 = num2;
                break;
                case'*':
                result = num1 * num2;
                displayfield.setText(displayfield.getText() + " " + num2 + " =");
                operator2 = '*';
                operator = '=';
                num3 = num2;
                break;
                case'/':
                result = num1 / num2;
                displayfield.setText(displayfield.getText() + " " + num2 + " =");
                operator2 = '/';
                operator = '=';
                num3 = num2;
                break;
                case '=':

                switch(operator2){
                    case '+':                
                    result = num1+num3;
                    displayfield.setText(num1 + " + " + num3 + " =");
                    break;
                    case'-':
                    result = num1 - num3;
                    displayfield.setText(num1 + " - " + num3 + " =");
                    operator2 = '-';
                    operator = '=';
                    break;
                    case'*':
                    result = num1 * num3;
                    displayfield.setText(num1 + " * " + num3 + " =");
                    operator2 = '*';
                    operator = '=';
                    break;
                    case'/':
                    result = num1 / num3;
                    displayfield.setText(num1 + " / " + num3 + " =");
                    operator2 = '/';
                    operator = '=';
                    break;
                }
                break;
                case '\0':
                num1 = Double.parseDouble(textfield.getText()); 
                result = num1;
                displayfield.setText(num1 + " =");
            }

            textfield.setText(String.valueOf(result));    
            num1 = result;
        }
        
            if(e.getSource() == clrButton){
                
                if (str_check.isEmpty() == false && str_check.charAt(0)  == '0' || display_temp.isEmpty() == false && display_temp.charAt(display_temp.length()-1) == '='){// check to see if the string is empty if not check to see if there's a 0 at text field
                    //if both condition met than clear the display and make the num1 zero
                    // or if display isn't empty than and display have an = at then end clear everything
                    displayfield.setText("");
                    num1=0;
                    num2=0;
                    num3=0;
                    operator2 = '\0';
                    operator = '\0';
                    result = 0;
                    
                }
            textfield.setText("0");
        }
        if(e.getSource() == delButton){
            String str = textfield.getText();
            textfield.setText("");
            for(int i =0; i < str.length()-1; i++){
                textfield.setText(textfield.getText() + str.charAt(i));
            }
        }


        if(e.getSource() == negButton){
            double temp = Double.parseDouble(textfield.getText());
            temp *=-1;

            
            textfield.setText(String.valueOf(temp));
            
            
        }
        textfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);// than will put it back to the right
        displayfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        // without this there's going to be a visiable bug in which it is 0.0-
    }
}

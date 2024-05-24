package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;

public class CalculatorMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		mostrarSplash();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorMain frame = new CalculatorMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculatorMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 400, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
				
		JLabel label = new JLabel("Cálculadora IMC e Metabolismo Basal Harris-Benedict");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(203, 20, 186, 14);
		contentPane.add(label);
				
		JPanel painelPeso = new JPanel();
		painelPeso.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPeso.setBackground(Color.DARK_GRAY);
		painelPeso.setLayout(new GridLayout());
		
		JLabel labelWeight = new JLabel("Peso (kg):");
		labelWeight.setForeground(Color.WHITE);
		labelWeight.setFont(new Font("Tahoma", Font.PLAIN, 18));
		painelPeso.add(labelWeight);
		
		JTextField textFieldWeight = new JTextField(3);
		textFieldWeight.setBackground(Color.WHITE);
		textFieldWeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c) || textFieldWeight.getText().length() >= 3) {
					evt.consume();
				}
			}
		});
		painelPeso.add(textFieldWeight);
		contentPane.add(painelPeso);
		
		
		JPanel painelAltura = new JPanel();
		painelAltura.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelAltura.setBackground(Color.DARK_GRAY);
		painelAltura.setLayout(new GridLayout());
		
		JLabel labelHeight = new JLabel("Altura (cm):");
		labelHeight.setForeground(Color.WHITE);
		labelHeight.setFont(new Font("Tahoma", Font.PLAIN, 18));
		painelAltura.add(labelHeight);
		
		JTextField textFieldHeight = new JTextField();
		textFieldHeight.setBackground(Color.WHITE);
		textFieldHeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c) || textFieldHeight.getText().length() >= 3) {
					evt.consume();
				}
			}
		});
		painelAltura.add(textFieldHeight);
		contentPane.add(painelAltura);
		
		
		JPanel painelIdade = new JPanel();
		painelIdade.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelIdade.setBackground(Color.DARK_GRAY);
		painelIdade.setLayout(new GridLayout());
		
		JLabel labelAge = new JLabel("Idade (anos):");
		labelAge.setForeground(Color.WHITE);
		labelAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		painelIdade.add(labelAge);

		
		JTextField textFieldAge = new JTextField();
		textFieldAge.setBackground(Color.WHITE);
		textFieldAge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c) || textFieldAge.getText().length() >= 3) {
					evt.consume();
				}
			}
		});
		painelIdade.add(textFieldAge);
		contentPane.add(painelIdade);
		
		JPanel painelSexo = new JPanel();
		painelSexo.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelSexo.setBackground(Color.DARK_GRAY);
		painelSexo.setLayout(new GridLayout());
		
		JLabel labelSex = new JLabel("Sexo:");
		labelSex.setForeground(Color.WHITE);
		labelSex.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelSex.setBounds(320, 180, 91, 14);
		painelSexo.add(labelSex);
	
		ButtonGroup buttonGroup = new ButtonGroup();
	
		JRadioButton radioButtonMale = new JRadioButton("Masculino", false);
		radioButtonMale.setForeground(Color.WHITE);
		radioButtonMale.setBackground(Color.DARK_GRAY);
		radioButtonMale.setFont(new Font("Tahoma", Font.PLAIN, 18));
		radioButtonMale.setBounds(425, 156, 149, 23);
		
		JRadioButton radioButtonFemale = new JRadioButton("Feminino", false);
		radioButtonFemale.setForeground(Color.WHITE);
		radioButtonFemale.setBackground(Color.DARK_GRAY);
		radioButtonFemale.setFont(new Font("Tahoma", Font.PLAIN, 18));
		radioButtonFemale.setBounds(425, 201, 149, 23);
		
		buttonGroup.add(radioButtonMale);
		buttonGroup.add(radioButtonFemale);
		
		painelSexo.add(radioButtonMale);
		painelSexo.add(radioButtonFemale);
		contentPane.add(painelSexo);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelBotoes.setBackground(Color.DARK_GRAY);
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
			
		JPanel painelResultado = new JPanel();
		painelResultado.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelResultado.setBackground(Color.DARK_GRAY);
		painelResultado.setLayout(new BoxLayout(painelResultado, BoxLayout.Y_AXIS));
		
		JLabel results = new JLabel("Resultados: ");
		results.setFont(new Font("Tahoma", Font.PLAIN, 18));
		results.setForeground(Color.WHITE);
		painelResultado.add(results);
		
		JLabel resultadoImc = new JLabel();
		painelResultado.add(resultadoImc);
		
		JLabel resultadoMetabolismo = new JLabel();
		resultadoMetabolismo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resultadoMetabolismo.setForeground(Color.WHITE);
		painelResultado.add(resultadoMetabolismo);
		
		contentPane.add(painelResultado);
		
		JButton calcButton = new JButton("Calcular");
		calcButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		calcButton.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					double height = Double.parseDouble(textFieldHeight.getText());
					double weight = Double.parseDouble(textFieldWeight.getText());
					int age = Integer.parseInt(textFieldAge.getText());
														
					JLabel labelImc = new JLabel(resultadoImc(calcularImc(weight, height)));
					labelImc.setForeground(Color.WHITE);
					labelImc.setFont(new Font("Tahoma", Font.PLAIN, 18));

					resultadoImc.setFont(new Font("Tahoma", Font.PLAIN, 18));
					resultadoImc.setForeground(Color.WHITE);
					resultadoImc.setText(resultadoImc(calcularImc(weight, height)));
			
					if(radioButtonMale.isSelected()) {
						resultadoMetabolismo.setFont(new Font("Tahoma", Font.PLAIN, 18));
						resultadoMetabolismo.setForeground(Color.WHITE);
						resultadoMetabolismo.setText("Seu metabolismo é: " + String.format("%.2f", calcularMetabolismoBasalMasculino(weight, height, age)));
					}
					else if (radioButtonFemale.isSelected()) {
						resultadoMetabolismo.setFont(new Font("Tahoma", Font.PLAIN, 18));
						resultadoMetabolismo.setForeground(Color.WHITE);
						resultadoMetabolismo.setText("Seu metabolismo é: " + String.format("%.2f", calcularMetabolismoBasalFeminino(weight, height, age)));

					}					
				} catch (NullPointerException e2) {
					System.err.println("Erro - Campos vazios");
					JLabel errorPanel = new JLabel("Erro - Campos vazios");
					errorPanel.setForeground(Color.WHITE);
					errorPanel.setFont(new Font("Tahoma", Font.PLAIN, 18));
					contentPane.add(errorPanel);
				} catch (ArithmeticException e2) {
					System.err.println("Erro - Divisão por 0");
					JLabel errorPanel = new JLabel("Erro - Divisão por 0");
					errorPanel.setForeground(Color.WHITE);
					errorPanel.setFont(new Font("Tahoma", Font.PLAIN, 18));
					contentPane.add(errorPanel);
				} catch (NumberFormatException e2) {
					System.out.println("Erro - Entrada inválida - Insira um número");
					JLabel errorPanel = new JLabel("Erro - Entrada inválida - Insira um número");
					errorPanel.setForeground(Color.WHITE);
					errorPanel.setFont(new Font("Tahoma", Font.PLAIN, 18));
					contentPane.add(errorPanel);
				}				
				
			}
		});

		contentPane.add(painelResultado);
		painelBotoes.add(calcButton);
		
		JButton botaoLimpar = new JButton("Limpar campos");
		botaoLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botaoLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldAge.setText(null);				
				textFieldHeight.setText(null);				
				textFieldWeight.setText(null);
				resultadoImc.setText(null);
				resultadoMetabolismo.setText(null);
			}
		});
		painelBotoes.add(botaoLimpar);
		
				
		JButton closeButton = new JButton("Fechar");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		painelBotoes.add(closeButton);
		contentPane.add(painelBotoes);
	

		setContentPane(contentPane);
	}

	private static void mostrarSplash() {
		// Criando a tela de carregamento
		JWindow window = new JWindow();
		ImageIcon imageIcon = new ImageIcon("splash.jpg");
		JLabel label = new JLabel(imageIcon);
		window.getContentPane().add(label);
		window.setBounds(500, 150, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		window.setVisible(true);
		try {
		Thread.sleep(5000); // Mantém a tela de carregamento visível por 5 segundos
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		window.dispose(); // Fecha a tela de carregamento
		}
	
	private static double calcularImc(double peso, double altura) {
		double imc = peso / (Math.pow((altura/100), 2));
		return imc;
	}

	private static String resultadoImc(double imc) {
		String imcFormatado = String.format("%.2f", imc);
		if(imc < 18.5) {
			return "Seu IMC é: " + imcFormatado + " - Abaixo do peso: IMC menor que 18,5";
		} else if (imc >= 18.5 && imc <= 24.9) {
			return "Seu IMC é: " + imcFormatado + " - Peso normal: IMC entre 18,5 e 24,9";
		} else if (imc > 25 && imc <= 29.9) {
			return "Seu IMC é: " + imcFormatado + " - Obesidade grau I: IMC entre 30 e 34,9";
		} else if (imc > 30 && imc <= 34.9) {
			return "Seu IMC é: " + imcFormatado + " - Obesidade grau I: IMC entre 30 e 34,9";		
		} else if (imc > 35 && imc <= 39.9) {
			return "Seu IMC é: " + imcFormatado + " - Obesidade grau II (severa): IMC entre 35 e 39,9";
		} else {
			return "Seu IMC é: " + imcFormatado + " - Obesidade grau III (mórbida): IMC maior que 40";
		}
	}
	
	private static double calcularMetabolismoBasalMasculino(double peso, double altura, int idade) {
		double metabolismo = 66 + (13.7 * peso) + (5 * altura) - (6.8 * idade);
		return metabolismo;
	}
	
	private static double calcularMetabolismoBasalFeminino(double peso, double altura, int idade) {
		double metabolismo = 655 + (9.6 * peso) + (1.8 * altura) - (4.7 * idade);
		return metabolismo;
	}
		
}



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
				
		JLabel labelTitulo = new JLabel("Cálculadora IMC e Metabolismo Basal Harris-Benedict");
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTitulo.setBounds(203, 20, 186, 14);
		contentPane.add(labelTitulo);
		
		JPanel painelNome = new JPanel();
		painelNome.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelNome.setBackground(Color.DARK_GRAY);
		painelNome.setLayout(new GridLayout());
		contentPane.add(painelNome);
		
		JLabel labelNome = new JLabel("Nome (primeiro):");
		labelNome.setForeground(Color.WHITE);
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		painelNome.add(labelNome);
		
		JTextField textFieldNome = new JTextField(3);
		textFieldNome.setBackground(Color.WHITE);
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isAlphabetic(c) || textFieldNome.getText().length() >= 12) {
					evt.consume();
				}
			}
		});
		painelNome.add(textFieldNome);
	
		JPanel painelPeso = new JPanel();
		painelPeso.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPeso.setBackground(Color.DARK_GRAY);
		painelPeso.setLayout(new GridLayout());
		contentPane.add(painelPeso);
		
		JLabel labelPeso = new JLabel("Peso (kg):");
		labelPeso.setForeground(Color.WHITE);
		labelPeso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		painelPeso.add(labelPeso);
		
		JTextField textFieldPeso = new JTextField(3);
		textFieldPeso.setBackground(Color.WHITE);
		textFieldPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c) || textFieldPeso.getText().length() >= 3) {
					evt.consume();
				}
			}
		});
		painelPeso.add(textFieldPeso);
		
		JPanel painelAltura = new JPanel();
		painelAltura.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelAltura.setBackground(Color.DARK_GRAY);
		painelAltura.setLayout(new GridLayout());
		contentPane.add(painelAltura);
		
		JLabel labelAltura = new JLabel("Altura (cm):");
		labelAltura.setForeground(Color.WHITE);
		labelAltura.setFont(new Font("Tahoma", Font.PLAIN, 18));
		painelAltura.add(labelAltura);
		
		JTextField textFieldAltura = new JTextField();
		textFieldAltura.setBackground(Color.WHITE);
		textFieldAltura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c) || textFieldAltura.getText().length() >= 3) {
					evt.consume();
				}
			}
		});
		painelAltura.add(textFieldAltura);
				
		JPanel painelIdade = new JPanel();
		painelIdade.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelIdade.setBackground(Color.DARK_GRAY);
		painelIdade.setLayout(new GridLayout());
		contentPane.add(painelIdade);
		
		JLabel labelIdade = new JLabel("Idade (anos):");
		labelIdade.setForeground(Color.WHITE);
		labelIdade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		painelIdade.add(labelIdade);
		
		JTextField textFieldIdade = new JTextField();
		textFieldIdade.setBackground(Color.WHITE);
		textFieldIdade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c) || textFieldIdade.getText().length() >= 3) {
					evt.consume();
				}
			}
		});
		painelIdade.add(textFieldIdade);
		
		JPanel painelSexo = new JPanel();
		painelSexo.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelSexo.setBackground(Color.DARK_GRAY);
		painelSexo.setLayout(new GridLayout());
		contentPane.add(painelSexo);
		
		JLabel labelSexo = new JLabel("Sexo:");
		labelSexo.setForeground(Color.WHITE);
		labelSexo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelSexo.setBounds(320, 180, 91, 14);
		painelSexo.add(labelSexo);
	
		ButtonGroup buttonGroup = new ButtonGroup();
	
		JRadioButton radioButtonMasculino = new JRadioButton("Masculino", false);
		radioButtonMasculino.setForeground(Color.WHITE);
		radioButtonMasculino.setBackground(Color.DARK_GRAY);
		radioButtonMasculino.setFont(new Font("Tahoma", Font.PLAIN, 18));
		radioButtonMasculino.setBounds(425, 156, 149, 23);
		
		JRadioButton radioButtonFeminino = new JRadioButton("Feminino", false);
		radioButtonFeminino.setForeground(Color.WHITE);
		radioButtonFeminino.setBackground(Color.DARK_GRAY);
		radioButtonFeminino.setFont(new Font("Tahoma", Font.PLAIN, 18));
		radioButtonFeminino.setBounds(425, 201, 149, 23);
		
		buttonGroup.add(radioButtonMasculino);
		buttonGroup.add(radioButtonFeminino);
		
		painelSexo.add(radioButtonMasculino);
		painelSexo.add(radioButtonFeminino);
		
		JPanel painelBotoesResultado = new JPanel();
		painelBotoesResultado.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelBotoesResultado.setBackground(Color.DARK_GRAY);
		painelBotoesResultado.setLayout(new GridLayout());
		contentPane.add(painelBotoesResultado);	
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelBotoes.setBackground(Color.DARK_GRAY);
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
		painelBotoesResultado.add(painelBotoes);
		
		JPanel painelResultado = new JPanel();
		painelResultado.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelResultado.setBackground(Color.DARK_GRAY);
		painelResultado.setLayout(new BoxLayout(painelResultado, BoxLayout.Y_AXIS));
		painelBotoesResultado.add(painelResultado);
		
		JLabel labelResultados = new JLabel("Resultados: ");
		labelResultados.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelResultados.setForeground(Color.WHITE);
		painelResultado.add(labelResultados);
		
		JLabel resultadoImc = new JLabel();
		resultadoImc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resultadoImc.setForeground(Color.WHITE);
		painelResultado.add(resultadoImc);
		
		JLabel resultadoMetabolismo = new JLabel();
		resultadoMetabolismo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resultadoMetabolismo.setForeground(Color.WHITE);
		painelResultado.add(resultadoMetabolismo);
		
		JLabel mensagemErro = new JLabel();
		mensagemErro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mensagemErro.setForeground(Color.RED);
		painelResultado.add(mensagemErro);
				
		JButton botaoCalcular = new JButton("Calcular");
		botaoCalcular.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botaoCalcular.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					mensagemErro.setText(null);
					
					double altura = Double.parseDouble(textFieldAltura.getText());
					double peso = Double.parseDouble(textFieldPeso.getText());
					int idade = Integer.parseInt(textFieldIdade.getText());

					resultadoImc.setText(resultadoImc(calcularImc(peso, altura)));
			
					resultadoMetabolismo.setFont(new Font("Tahoma", Font.PLAIN, 18));
					resultadoMetabolismo.setForeground(Color.WHITE);
					
					if(radioButtonMasculino.isSelected()) {
						resultadoMetabolismo.setText("Seu metabolismo é: " + String.format("%.2f", calcularMetabolismoBasalMasculino(peso, altura, idade)));
					}
					else if (radioButtonFeminino.isSelected()) {
						resultadoMetabolismo.setText("Seu metabolismo é: " + String.format("%.2f", calcularMetabolismoBasalFeminino(peso, altura, idade)));

					}					
				} catch (NullPointerException e2) {
					System.err.println("Erro - Campos vazios - " + e2);
					mensagemErro.setText("Erro - Campos vazios");
				} catch (ArithmeticException e2) {
					System.err.println("Erro - Divisão por 0 - " + e2);
					mensagemErro.setText("Erro - Divisão por 0");
				} catch (NumberFormatException e2) {
					System.err.println("Erro - Entrada inválida - " + e2);
					mensagemErro.setText("Erro - Entrada inválida - Insira um número");
				}				
				
			}
		});
		painelBotoes.add(botaoCalcular);
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botaoLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText(null);
				textFieldIdade.setText(null);				
				textFieldAltura.setText(null);				
				textFieldPeso.setText(null);
				resultadoImc.setText(null);
				resultadoMetabolismo.setText(null);
				mensagemErro.setText(null);
			}
		});
		painelBotoes.add(botaoLimpar);
						
		JButton botaoFechar = new JButton("Fechar");
		botaoFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		botaoFechar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		painelBotoes.add(botaoFechar);

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

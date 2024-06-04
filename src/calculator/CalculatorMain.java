package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.JobPrioritySupported;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class CalculatorMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final String jdbcUrl = "jdbc:sqlite:calculadoraImc.db";
	private static String[] lista = new String[6];
	private static JList<String> listaHistorico = new JList<String>(lista);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectHistorico(lista, listaHistorico);
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
		setBounds(700, 300, 1000, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		JPanel painelTitulo = new JPanel();
		painelTitulo.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelTitulo.setBackground(Color.LIGHT_GRAY);
		painelTitulo.setLayout(new GridLayout(1,1));
		contentPane.add(painelTitulo);
		
		JLabel labelTitulo = new JLabel("Calculadora IMC e Metabolismo Basal Harris-Benedict");
		labelTitulo.setForeground(Color.BLACK);
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		painelTitulo.add(labelTitulo);
			
		JPanel painelNome = new JPanel();
		painelNome.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelNome.setBackground(Color.DARK_GRAY);
		painelNome.setLayout(new GridLayout(1,2));
		contentPane.add(painelNome);
		
		JLabel labelNome = new JLabel("Nome (primeiro)*:");
		labelNome.setForeground(Color.WHITE);
		labelNome.setFont(new Font("Arial", Font.PLAIN, 16));
		painelNome.add(labelNome);
		
		JTextField textFieldNome = new JTextField(15);
		textFieldNome.setBackground(Color.WHITE);
		textFieldNome.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		textFieldNome.setFont(new Font("Arial", Font.PLAIN, 16));
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isAlphabetic(c) || textFieldNome.getText().length() >= 15) {
					evt.consume();
				}
			}
		});
		painelNome.add(textFieldNome);
	
		JPanel painelPeso = new JPanel();
		painelPeso.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPeso.setBackground(Color.DARK_GRAY);
		painelPeso.setLayout(new GridLayout(1,2));
		contentPane.add(painelPeso);
		
		JLabel labelPeso = new JLabel("Peso (kg)*:");
		labelPeso.setForeground(Color.WHITE);
		labelPeso.setFont(new Font("Arial", Font.PLAIN, 16));
		painelPeso.add(labelPeso);
		
		JTextField textFieldPeso = new JTextField(3);
		textFieldPeso.setBackground(Color.WHITE);
		textFieldPeso.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		textFieldPeso.setFont(new Font("Arial", Font.PLAIN, 16));
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
		painelAltura.setLayout(new GridLayout(1,2));
		contentPane.add(painelAltura);
		
		JLabel labelAltura = new JLabel("Altura (cm)*:");
		labelAltura.setForeground(Color.WHITE);
		labelAltura.setFont(new Font("Arial", Font.PLAIN, 16));
		painelAltura.add(labelAltura);
		
		JTextField textFieldAltura = new JTextField();
		textFieldAltura.setBackground(Color.WHITE);
		textFieldAltura.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		textFieldAltura.setFont(new Font("Arial", Font.PLAIN, 16));
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
		painelIdade.setLayout(new GridLayout(2,2));
		contentPane.add(painelIdade);
		
		JLabel labelIdade = new JLabel("Idade (anos)*:");
		labelIdade.setForeground(Color.WHITE);
		labelIdade.setFont(new Font("Arial", Font.PLAIN, 16));
		painelIdade.add(labelIdade);
		
		JTextField textFieldIdade = new JTextField();
		textFieldIdade.setBackground(Color.WHITE);
		textFieldIdade.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		textFieldIdade.setFont(new Font("Arial", Font.PLAIN, 16));
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
		
		JLabel labelCamposObrigatorios = new JLabel("(*) Campos obrigatórios");
		labelCamposObrigatorios.setForeground(Color.YELLOW);
		labelCamposObrigatorios.setFont(new Font("Arial", Font.BOLD, 12));
		painelIdade.add(labelCamposObrigatorios);
			
		JPanel painelSexo = new JPanel();
		painelSexo.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelSexo.setBackground(Color.DARK_GRAY);
		painelSexo.setLayout(new GridLayout(1, 2));
		contentPane.add(painelSexo);
		
		JLabel labelSexo = new JLabel("Sexo:");
		labelSexo.setForeground(Color.WHITE);
		labelSexo.setFont(new Font("Arial", Font.PLAIN, 16));
		labelSexo.setBounds(320, 180, 91, 14);
		painelSexo.add(labelSexo);
	
		ButtonGroup buttonGroup = new ButtonGroup();
	
		JRadioButton radioButtonMasculino = new JRadioButton("Masculino", true);
		radioButtonMasculino.setForeground(Color.WHITE);
		radioButtonMasculino.setBackground(Color.DARK_GRAY);
		radioButtonMasculino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioButtonMasculino.setBounds(425, 156, 149, 23);
		
		JRadioButton radioButtonFeminino = new JRadioButton("Feminino", false);
		radioButtonFeminino.setForeground(Color.WHITE);
		radioButtonFeminino.setBackground(Color.DARK_GRAY);
		radioButtonFeminino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioButtonFeminino.setBounds(425, 201, 149, 23);
		
		buttonGroup.add(radioButtonMasculino);
		buttonGroup.add(radioButtonFeminino);
		
		painelSexo.add(radioButtonMasculino);
		painelSexo.add(radioButtonFeminino);

		JPanel painelBotoes = new JPanel();
		painelBotoes.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelBotoes.setBackground(Color.DARK_GRAY);
		painelBotoes.setLayout(new GridLayout());
		contentPane.add(painelBotoes);
		
		JSeparator separatorBotoes = new JSeparator();
		separatorBotoes.setForeground(Color.LIGHT_GRAY);
		separatorBotoes.setBackground(Color.LIGHT_GRAY);
		contentPane.add(separatorBotoes);
					
		JPanel painelResultado = new JPanel();
		painelResultado.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelResultado.setBackground(Color.DARK_GRAY);
		painelResultado.setLayout(new GridLayout(5,2));
		contentPane.add(painelResultado);
		
		JLabel labelResultados = new JLabel("Resultados");
		labelResultados.setFont(new Font("Arial", Font.BOLD, 20));
		labelResultados.setForeground(Color.WHITE);
		painelResultado.add(labelResultados);
		
		JSeparator separatorResultado = new JSeparator();
		separatorResultado.setBackground(Color.DARK_GRAY);
		separatorResultado.setForeground(Color.DARK_GRAY);
		painelResultado.add(separatorResultado);
		
		JLabel resultadoImc = new JLabel("IMC: não calculado");
		resultadoImc.setFont(new Font("Arial", Font.PLAIN, 16));
		resultadoImc.setForeground(Color.WHITE);
		painelResultado.add(resultadoImc);
		
		JLabel resultadoMetabolismo = new JLabel("Metabolismo: não calculado");
		resultadoMetabolismo.setFont(new Font("Arial", Font.PLAIN, 16));
		resultadoMetabolismo.setForeground(Color.WHITE);
		painelResultado.add(resultadoMetabolismo);
		
		JLabel mensagemErro = new JLabel(" ");
		mensagemErro.setFont(new Font("Arial", Font.PLAIN, 16));
		mensagemErro.setForeground(Color.YELLOW);
		painelResultado.add(mensagemErro);
	
		JSeparator separatorHistorico = new JSeparator();
		separatorHistorico.setForeground(Color.LIGHT_GRAY);
		separatorHistorico.setBackground(Color.LIGHT_GRAY);
		contentPane.add(separatorHistorico);
		
		JPanel painelHistorico = new JPanel();
		painelHistorico.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelHistorico.setBackground(Color.DARK_GRAY);
		painelHistorico.setLayout(new GridLayout(3,1));
		contentPane.add(painelHistorico);
		
		JLabel labelHistorico = new JLabel("Histórico");
		labelHistorico.setForeground(Color.WHITE);
		labelHistorico.setFont(new Font("Arial", Font.BOLD, 20));
		painelHistorico.add(labelHistorico);
		
		listaHistorico.setBorder(new EmptyBorder(5, 5, 5, 5));
		listaHistorico.setFont(new Font("Arial", Font.BOLD, 13));
		listaHistorico.setBackground(Color.DARK_GRAY);
		listaHistorico.setForeground(Color.WHITE);
		listaHistorico.setPreferredSize(new Dimension(200, 100));
		painelHistorico.add(listaHistorico);
	
		JPanel painelBotoesHistorico = new JPanel();
		painelBotoesHistorico.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelBotoesHistorico.setBackground(Color.DARK_GRAY);
		painelBotoesHistorico.setLayout(new BorderLayout());
		painelHistorico.add(painelBotoesHistorico);
		
		JFrame confirmationFrame = new JFrame("Confirmação");
		confirmationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		confirmationFrame.setSize(300, 200);
		confirmationFrame.setLocationRelativeTo(null);
	
		JButton botaoLimparHistorico = new JButton("Limpar Histórico");
		botaoLimparHistorico.setFont(new Font("Arial", Font.BOLD, 16));
		botaoLimparHistorico.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				int result = JOptionPane.showConfirmDialog(confirmationFrame, "Tem certeza que deseja excluir todo o histórico?", "Confirmação", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					deleteHistorico(lista, listaHistorico);
					JOptionPane.showMessageDialog(confirmationFrame, "Histórico excluído com sucesso");
				} else {
//					JOptionPane.showMessageDialog(confirmationFrame, "Ação cancelada");
				}
				
				
			}
		});
		painelBotoesHistorico.add(botaoLimparHistorico, BorderLayout.WEST);
		
		JButton botaoCalcular = new JButton("Calcular");
		botaoCalcular.setFont(new Font("Arial", Font.BOLD, 16));
		botaoCalcular.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection connection = DriverManager.getConnection(jdbcUrl);
					Statement statement = connection.createStatement();
					System.out.println("Conexão com o database - INSERT");
					
					mensagemErro.setText(" ");

					String nome = textFieldNome.getText();
					double altura = Double.parseDouble(textFieldAltura.getText());
					double peso = Double.parseDouble(textFieldPeso.getText());
					int idade = Integer.parseInt(textFieldIdade.getText());
					String imcResposta;
					String metabolismoResposta;		
					
					if(nome == null || nome.equals("")) {
						textFieldNome.setText(null);
						mensagemErro.setText("Nome não preenchido");
					} else if(altura == 0) {
						textFieldAltura.setText(null);
						resultadoImc.setText("IMC: não calculado");
						resultadoMetabolismo.setText("Metabolismo: não calculado");
						mensagemErro.setText("Altura não preenchida");
					} else if (peso == 0) {
						textFieldPeso.setText(null);
						resultadoImc.setText("IMC: não calculado");
						resultadoMetabolismo.setText("Metabolismo: não calculado");
						mensagemErro.setText("Peso não preenchido");					
					} else if (idade == 0) {
						textFieldIdade.setText(null);
						resultadoImc.setText("IMC: não calculado");
						resultadoMetabolismo.setText("Metabolismo: não calculado");
						mensagemErro.setText("Idade não preenchida");
					} else {
						imcResposta = resultadoImc(calcularImc(peso, altura));						
						resultadoImc.setText(imcResposta);
						
						
						
						if(radioButtonMasculino.isSelected()) {
							metabolismoResposta = String.format("%.2f", calcularMetabolismoBasalMasculino(peso, altura, idade));
							resultadoMetabolismo.setText("Metabolismo: " + metabolismoResposta);

							statement.executeUpdate("insert into historico values ('" + textFieldNome.getText() + "', '" + textFieldPeso.getText() + "',"
									+ " '"+ textFieldAltura.getText() + "', '" + textFieldIdade.getText() + "', '" + metabolismoResposta + "', '"+ imcResposta + "')");
							selectHistorico(lista, listaHistorico);
						}
						else if (radioButtonFeminino.isSelected()) {
							metabolismoResposta = String.format("%.2f", calcularMetabolismoBasalFeminino(peso, altura, idade));
							resultadoMetabolismo.setText("Metabolismo: " + metabolismoResposta);
							
							statement.executeUpdate("insert into historico values ('" + textFieldNome.getText() + "', '" + textFieldPeso.getText() + "',"
									+ " '"+ textFieldAltura.getText() + "', '" + textFieldIdade.getText() + "', '" + metabolismoResposta + "', '"+ imcResposta + "')");
							selectHistorico(lista, listaHistorico);
						}										
					}
					
				} catch (NullPointerException e2) {
					System.err.println("Erro - Campos vazios - " + e2);
					mensagemErro.setText("Erro - Campos vazios");
				} catch (ArithmeticException e2) {
					System.err.println("Erro - Divisão por 0 - " + e2);
					mensagemErro.setText("Erro - Divisão por 0");
				} catch (NumberFormatException e2) {
					System.err.println("Erro - Entrada inválida - " + e2);
					mensagemErro.setText("Campos obrigatórios não preenchidos");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}				
				
			}
		});
		painelBotoes.add(botaoCalcular);
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setFont(new Font("Arial", Font.BOLD, 16));
		botaoLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText(null);
				textFieldIdade.setText(null);				
				textFieldAltura.setText(null);				
				textFieldPeso.setText(null);
				resultadoImc.setText("IMC: não calculado");
				resultadoMetabolismo.setText("Metabolismo: não calculado");
				mensagemErro.setText(" ");
			}
		});
		painelBotoes.add(botaoLimpar);
						
		JSeparator separatorFinal = new JSeparator();
		separatorFinal.setForeground(Color.LIGHT_GRAY);
		separatorFinal.setBackground(Color.LIGHT_GRAY);
		contentPane.add(separatorFinal);

		JPanel painelFinal = new JPanel();
		painelFinal.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelFinal.setBackground(Color.DARK_GRAY);
		painelFinal.setLayout(new GridLayout());
		contentPane.add(painelFinal);
		
		JPanel painelIdentificacao = new JPanel();
		painelIdentificacao.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelIdentificacao.setBackground(Color.DARK_GRAY);
		painelIdentificacao.setLayout(new BoxLayout(painelIdentificacao, BoxLayout.Y_AXIS));
		painelFinal.add(painelIdentificacao);
		
		JLabel labelUniversidade = new JLabel("Ceub - 2024");
		labelUniversidade.setForeground(Color.WHITE);
		labelUniversidade.setFont(new Font("Arial", Font.PLAIN, 12));
		painelIdentificacao.add(labelUniversidade);
		
		JLabel labelDisciplina= new JLabel("Programação Orientada a Objetos III");
		labelDisciplina.setForeground(Color.WHITE);
		labelDisciplina.setFont(new Font("Arial", Font.PLAIN, 12));
		painelIdentificacao.add(labelDisciplina);
		
		JLabel labelProfessor= new JLabel("Prof. Romes Heriberto Pires de Araujo");
		labelProfessor.setForeground(Color.WHITE);
		labelProfessor.setFont(new Font("Arial", Font.PLAIN, 12));
		painelIdentificacao.add(labelProfessor);
		
		JLabel labelAluno= new JLabel("Rodrigo Lobo Gaia da Silva - 72151181");
		labelAluno.setForeground(Color.WHITE);
		labelAluno.setFont(new Font("Arial", Font.PLAIN, 12));
		painelIdentificacao.add(labelAluno);
		
		JButton botaoFechar = new JButton("Fechar");
		botaoFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		botaoFechar.setFont(new Font("Arial", Font.BOLD, 16));
		painelFinal.add(botaoFechar);
			
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuTema = new JMenu("Tema");
		menuBar.add(menuTema);
		
		JRadioButtonMenuItem temaEscuro = new JRadioButtonMenuItem("Escuro", true);
		JRadioButtonMenuItem temaClaro = new JRadioButtonMenuItem("Claro", false);
		
		temaEscuro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				contentPane.setBackground(Color.LIGHT_GRAY);
			
				painelNome.setBackground(Color.DARK_GRAY);
				labelNome.setForeground(Color.WHITE);
				textFieldNome.setBackground(Color.WHITE);
				
				painelPeso.setBackground(Color.DARK_GRAY);
				labelPeso.setForeground(Color.WHITE);
				textFieldPeso.setBackground(Color.WHITE);
				
				painelAltura.setBackground(Color.DARK_GRAY);
				labelAltura.setForeground(Color.WHITE);
				textFieldAltura.setBackground(Color.WHITE);
				
				painelIdade.setBackground(Color.DARK_GRAY);
				labelIdade.setForeground(Color.WHITE);
				textFieldIdade.setBackground(Color.WHITE);
				
				labelCamposObrigatorios.setForeground(Color.YELLOW);
				
				painelSexo.setBackground(Color.DARK_GRAY);
				labelSexo.setForeground(Color.WHITE);
				
				painelBotoes.setBackground(Color.DARK_GRAY);
				radioButtonMasculino.setForeground(Color.WHITE);
				radioButtonMasculino.setBackground(Color.DARK_GRAY);
				radioButtonFeminino.setForeground(Color.WHITE);
				radioButtonFeminino.setBackground(Color.DARK_GRAY);
				
				painelResultado.setBackground(Color.DARK_GRAY);
				labelResultados.setForeground(Color.WHITE);
				separatorResultado.setBackground(Color.DARK_GRAY);
				separatorResultado.setForeground(Color.DARK_GRAY);
				resultadoImc.setForeground(Color.WHITE);
				resultadoMetabolismo.setForeground(Color.WHITE);
				
				painelHistorico.setBackground(Color.DARK_GRAY);
				labelHistorico.setForeground(Color.WHITE);
				painelBotoesHistorico.setBackground(Color.DARK_GRAY);
				listaHistorico.setBackground(Color.DARK_GRAY);
				listaHistorico.setForeground(Color.WHITE);

				mensagemErro.setForeground(Color.YELLOW);
				
				separatorFinal.setForeground(Color.LIGHT_GRAY);
				separatorFinal.setBackground(Color.LIGHT_GRAY);
				painelFinal.setBackground(Color.DARK_GRAY);
				painelIdentificacao.setBackground(Color.DARK_GRAY);


				labelUniversidade.setForeground(Color.WHITE);

				labelDisciplina.setForeground(Color.WHITE);

				labelProfessor.setForeground(Color.WHITE);

				labelAluno.setForeground(Color.WHITE);
			}
		});
		
		temaClaro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				contentPane.setBackground(Color.LIGHT_GRAY);
		
				painelNome.setBackground(Color.WHITE);
				labelNome.setForeground(Color.BLACK);
				textFieldNome.setBackground(Color.WHITE);
				
				painelPeso.setBackground(Color.WHITE);
				labelPeso.setForeground(Color.BLACK);
				textFieldPeso.setBackground(Color.WHITE);
				
				painelAltura.setBackground(Color.WHITE);
				labelAltura.setForeground(Color.BLACK);
				textFieldAltura.setBackground(Color.WHITE);
				
				painelIdade.setBackground(Color.WHITE);
				labelIdade.setForeground(Color.BLACK);
				textFieldIdade.setBackground(Color.WHITE);
				
				labelCamposObrigatorios.setForeground(Color.RED);
				
				painelSexo.setBackground(Color.WHITE);
				labelSexo.setForeground(Color.BLACK);
				
				painelBotoes.setBackground(Color.WHITE);
				radioButtonMasculino.setForeground(Color.BLACK);
				radioButtonMasculino.setBackground(Color.WHITE);
				radioButtonFeminino.setForeground(Color.BLACK);
				radioButtonFeminino.setBackground(Color.WHITE);
				
				painelResultado.setBackground(Color.WHITE);
				labelResultados.setForeground(Color.BLACK);
				separatorResultado.setBackground(Color.WHITE);
				separatorResultado.setForeground(Color.WHITE);
				resultadoImc.setForeground(Color.BLACK);
				resultadoMetabolismo.setForeground(Color.BLACK);
				
				painelHistorico.setBackground(Color.WHITE);
				labelHistorico.setForeground(Color.BLACK);
				painelBotoesHistorico.setBackground(Color.WHITE);
				listaHistorico.setBackground(Color.WHITE);
				listaHistorico.setForeground(Color.BLACK);
				
				mensagemErro.setForeground(Color.RED);
				
				separatorFinal.setForeground(Color.LIGHT_GRAY);
				separatorFinal.setBackground(Color.LIGHT_GRAY);
				painelFinal.setBackground(Color.WHITE);
				painelIdentificacao.setBackground(Color.WHITE);


				labelUniversidade.setForeground(Color.BLACK);

				labelDisciplina.setForeground(Color.BLACK);

				labelProfessor.setForeground(Color.BLACK);

				labelAluno.setForeground(Color.BLACK);
								
			}
		});
		
		ButtonGroup buttonGroupTema = new ButtonGroup();
		buttonGroupTema.add(temaEscuro);
		buttonGroupTema.add(temaClaro);
		
		menuTema.add(temaEscuro);
		menuTema.add(temaClaro);
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
	}
	
	private static double calcularImc(double peso, double altura) {
		double imc = peso / Math.pow(altura/100, 2);
		return imc;
	}

	private static String resultadoImc(double imc) {
		String imcFormatado = String.format("%.2f", imc);
		if(imc < 18.5) {
			return imcFormatado + " - Abaixo do peso: IMC menor que 18,5";
		} else if (imc >= 18.5 && imc <= 24.9) {
			return imcFormatado + " - Peso normal: IMC entre 18,5 e 24,9";
		} else if (imc > 25 && imc <= 29.9) {
			return imcFormatado + " - Obesidade grau I: IMC entre 30 e 34,9";
		} else if (imc > 30 && imc <= 34.9) {
			return imcFormatado + " - Obesidade grau I: IMC entre 30 e 34,9";		
		} else if (imc > 35 && imc <= 39.9) {
			return imcFormatado + " - Obesidade grau II (severa): IMC entre 35 e 39,9";
		} else {
			return imcFormatado + " - Obesidade grau III (mórbida): IMC maior que 40";
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
	
	private static void selectHistorico(String[] lista, JList listaHistorico) {
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			Statement statement = connection.createStatement();
			String sql = "select rowid, * from historico order by rowid desc limit 5";
			int rows = statement.executeUpdate(sql);
			ResultSet result = statement.executeQuery(sql);
			System.out.println("Conexão com o database - SELECT");
			System.out.println("Rows: " + rows);
			while(result.next()) {											
				Integer id = result.getInt("rowid");
				String nome = result.getString("nome");
				String peso = result.getString("peso");
				String altura = result.getString("altura");
				String idade = result.getString("idade");
				String metabolismo = result.getString("metabolismo");
				String resultadoImc = result.getString("resultadoImc");
				System.out.printf("%d | Nome: %s |Peso: %s kg | Altura: %s cm | Idade: %s anos | Metabolismo: %s | IMC: %s%n", id, nome, peso, altura, idade, metabolismo, resultadoImc);
				
				System.out.println("result.getRow(): " + result.getRow());
				lista[result.getRow()] = String.format("%d | Nome: %s | Peso: %s kg | Altura: %s cm | Idade: %s anos | Metabolismo: %s | IMC: %s%n", id, nome, peso, altura, idade, metabolismo, resultadoImc);
				listaHistorico.setListData(lista);
			
				
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
		
	private static void deleteHistorico(String[] lista, JList listaHistorico) {
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM HISTORICO");
			System.out.println("Conexão com o database - DELETE");
			
			for (int i=0; i<lista.length; i++) {
				lista[i] = "";
			}
			listaHistorico.setListData(lista);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private static void createHistorico() {

		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			Statement statement = connection.createStatement();
			String sqlCreate = "create table historico (nome varchar(15), peso varchar(3), altura varchar(3), idade varchar(3), metabolismo varchar(20), resultadoImc varchar(100))";			
			statement.executeUpdate(sqlCreate);
			System.out.println("Conexão criada");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
		
}

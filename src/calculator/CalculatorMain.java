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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
	private static String jdbcUrl = "jdbc:sqlite:calculadoraImc.db";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					mostrarSplash();
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
		
//		Connection connection;
//		Statement statement;
//		try {
//			connection = DriverManager.getConnection(jdbcUrl);
//			statement = connection.createStatement();
//			String sqlCreate = "create table historico (nome varchar(15), peso varchar(3), altura varchar(3), idade varchar(3), metabolismo varchar(20), resultadoImc varchar(100))";			
//			statement.executeUpdate(sqlCreate);
//			System.out.println("Conexão criada");
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 850, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		JLabel labelTitulo = new JLabel("Cálculadora IMC e Metabolismo Basal Harris-Benedict");
		labelTitulo.setForeground(Color.BLACK);
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitulo.setBounds(203, 20, 186, 14);
		contentPane.add(labelTitulo);
			
		JPanel painelNome = new JPanel();
		painelNome.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelNome.setBackground(Color.DARK_GRAY);
		painelNome.setLayout(new GridLayout(1,2));
		contentPane.add(painelNome);
		
		JLabel labelNome = new JLabel("Nome (primeiro)*:");
		labelNome.setForeground(Color.WHITE);
		labelNome.setFont(new Font("Arial", Font.PLAIN, 18));
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
		labelPeso.setFont(new Font("Arial", Font.PLAIN, 18));
		painelPeso.add(labelPeso);
		
		JTextField textFieldPeso = new JTextField(3);
		textFieldPeso.setBackground(Color.WHITE);
		textFieldPeso.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		textFieldPeso.setFont(new Font("Arial", Font.PLAIN, 18));
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
		labelAltura.setFont(new Font("Arial", Font.PLAIN, 18));
		painelAltura.add(labelAltura);
		
		JTextField textFieldAltura = new JTextField();
		textFieldAltura.setBackground(Color.WHITE);
		textFieldAltura.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		textFieldAltura.setFont(new Font("Arial", Font.PLAIN, 18));
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
		labelIdade.setFont(new Font("Arial", Font.PLAIN, 18));
		painelIdade.add(labelIdade);
		
		JTextField textFieldIdade = new JTextField();
		textFieldIdade.setBackground(Color.WHITE);
		textFieldIdade.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		textFieldIdade.setFont(new Font("Arial", Font.PLAIN, 18));
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
		labelCamposObrigatorios.setFont(new Font("Arial", Font.BOLD, 13));
		painelIdade.add(labelCamposObrigatorios);
			
		JPanel painelSexo = new JPanel();
		painelSexo.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelSexo.setBackground(Color.DARK_GRAY);
		painelSexo.setLayout(new GridLayout(1, 2));
		contentPane.add(painelSexo);
		
		JLabel labelSexo = new JLabel("Sexo:");
		labelSexo.setForeground(Color.WHITE);
		labelSexo.setFont(new Font("Arial", Font.PLAIN, 18));
		labelSexo.setBounds(320, 180, 91, 14);
		painelSexo.add(labelSexo);
	
		ButtonGroup buttonGroup = new ButtonGroup();
	
		JRadioButton radioButtonMasculino = new JRadioButton("Masculino", true);
		radioButtonMasculino.setForeground(Color.WHITE);
		radioButtonMasculino.setBackground(Color.DARK_GRAY);
		radioButtonMasculino.setFont(new Font("Arial", Font.PLAIN, 18));
		radioButtonMasculino.setBounds(425, 156, 149, 23);
		
		JRadioButton radioButtonFeminino = new JRadioButton("Feminino", false);
		radioButtonFeminino.setForeground(Color.WHITE);
		radioButtonFeminino.setBackground(Color.DARK_GRAY);
		radioButtonFeminino.setFont(new Font("Arial", Font.PLAIN, 18));
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
		painelResultado.setLayout(new GridLayout(6,2));
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
		resultadoImc.setFont(new Font("Arial", Font.PLAIN, 18));
		resultadoImc.setForeground(Color.WHITE);
		painelResultado.add(resultadoImc);
		
		JLabel resultadoMetabolismo = new JLabel("Metabolismo: não calculado");
		resultadoMetabolismo.setFont(new Font("Arial", Font.PLAIN, 18));
		resultadoMetabolismo.setForeground(Color.WHITE);
		painelResultado.add(resultadoMetabolismo);
		
		JLabel mensagemErro = new JLabel(" ");
		mensagemErro.setFont(new Font("Arial", Font.PLAIN, 18));
		mensagemErro.setForeground(Color.YELLOW);
		painelResultado.add(mensagemErro);
		
		JButton botaoHistorico = new JButton("Histórico");
		botaoHistorico.setFont(new Font("Arial", Font.PLAIN, 18));
		botaoHistorico.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame frameHistorico = new JFrame("Histórico");
				frameHistorico.setVisible(true);
				frameHistorico.setBounds(800, 400, 850, 550);

				JPanel painelHistorico = new JPanel();
				painelHistorico.setBorder(new EmptyBorder(5, 5, 5, 5));
				painelHistorico.setBackground(Color.LIGHT_GRAY);
				painelHistorico.setLayout(new BoxLayout(painelHistorico, BoxLayout.PAGE_AXIS));
				
				JLabel labelHistorico = new JLabel("Histórico");
				labelHistorico.setForeground(Color.BLACK);
				labelHistorico.setFont(new Font("Arial", Font.BOLD, 20));
				labelHistorico.setBounds(203, 20, 186, 14);
				
				painelHistorico.add(labelHistorico);
				
				JLabel labelResultado = new JLabel();
				labelResultado.setForeground(Color.BLACK);
				labelResultado.setFont(new Font("Arial", Font.BOLD, 20));
				labelResultado.setBounds(203, 20, 186, 14);
				
				try {
					Connection connection = DriverManager.getConnection(jdbcUrl);
					Statement statement = connection.createStatement();
					String sql = "select rowid, * from historico";
					int rows = statement.executeUpdate(sql);
					ResultSet result = statement.executeQuery(sql);
					System.out.println("Conexão com o database - SELECT");
					while(result.next()) {					
						Integer id = result.getInt("rowid");
						String nome = result.getString("nome");
						String peso = result.getString("peso");
						String altura = result.getString("altura");
						String idade = result.getString("idade");
						String metabolismo = result.getString("metabolismo");
						String resultadoImc = result.getString("resultadoImc");
						System.out.println(id + nome + peso + altura + idade + metabolismo + resultadoImc);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				

				
				JButton botaoLimparHistorico = new JButton("Limpar Histórico");
				botaoHistorico.setFont(new Font("Arial", Font.PLAIN, 18));
				botaoHistorico.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Connection connection = DriverManager.getConnection(jdbcUrl);
							Statement statement = connection.createStatement();
							statement.executeUpdate("DELETE FROM HISTORICO");
							System.out.println("Conexão com o database - DELETE");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				});
				painelHistorico.add(botaoLimparHistorico);
				
				JButton botaoFecharHistorico = new JButton("Fechar");
				botaoFecharHistorico.setFont(new Font("Arial", Font.PLAIN, 18));
				botaoFecharHistorico.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				painelHistorico.add(botaoFecharHistorico);
				
				frameHistorico.setContentPane(painelHistorico);
			}
		});
		painelResultado.add(botaoHistorico);
		
		JButton botaoCalcular = new JButton("Calcular");
		botaoCalcular.setFont(new Font("Arial", Font.PLAIN, 18));
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
	
						}
						else if (radioButtonFeminino.isSelected()) {
							metabolismoResposta = String.format("%.2f", calcularMetabolismoBasalFeminino(peso, altura, idade));
							resultadoMetabolismo.setText("Metabolismo: " + metabolismoResposta);
							
							statement.executeUpdate("insert into historico values ('" + textFieldNome.getText() + "', '" + textFieldPeso.getText() + "',"
									+ " '"+ textFieldAltura.getText() + "', '" + textFieldIdade.getText() + "', '" + metabolismoResposta + "', '"+ imcResposta + "')");
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
		botaoLimpar.setFont(new Font("Arial", Font.PLAIN, 18));
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
		botaoFechar.setFont(new Font("Arial", Font.PLAIN, 18));
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
				labelNome.setFont(new Font("Arial", Font.PLAIN, 18));
				textFieldNome.setBackground(Color.WHITE);
				textFieldNome.setFont(new Font("Arial", Font.PLAIN, 16));
				
				painelPeso.setBackground(Color.DARK_GRAY);
				labelPeso.setForeground(Color.WHITE);
				labelPeso.setFont(new Font("Arial", Font.PLAIN, 18));
				textFieldPeso.setBackground(Color.WHITE);
				textFieldPeso.setFont(new Font("Arial", Font.PLAIN, 18));
				
				painelAltura.setBackground(Color.DARK_GRAY);
				labelAltura.setForeground(Color.WHITE);
				labelAltura.setFont(new Font("Arial", Font.PLAIN, 18));
				textFieldAltura.setBackground(Color.WHITE);
				textFieldAltura.setFont(new Font("Arial", Font.PLAIN, 18));
				
				painelIdade.setBackground(Color.DARK_GRAY);
				labelIdade.setForeground(Color.WHITE);
				labelIdade.setFont(new Font("Arial", Font.PLAIN, 18));
				textFieldIdade.setBackground(Color.WHITE);
				textFieldIdade.setFont(new Font("Arial", Font.PLAIN, 18));
				
				labelCamposObrigatorios.setForeground(Color.YELLOW);
				labelCamposObrigatorios.setFont(new Font("Arial", Font.BOLD, 13));
				
				painelSexo.setBackground(Color.DARK_GRAY);
				labelSexo.setForeground(Color.WHITE);
				labelSexo.setFont(new Font("Arial", Font.PLAIN, 18));
				
				painelBotoes.setBackground(Color.DARK_GRAY);
				radioButtonMasculino.setForeground(Color.WHITE);
				radioButtonMasculino.setBackground(Color.DARK_GRAY);
				radioButtonFeminino.setForeground(Color.WHITE);
				radioButtonFeminino.setBackground(Color.DARK_GRAY);
				
				painelResultado.setBackground(Color.DARK_GRAY);
				labelResultados.setFont(new Font("Arial", Font.BOLD, 20));
				labelResultados.setForeground(Color.WHITE);
				separatorResultado.setBackground(Color.DARK_GRAY);
				separatorResultado.setForeground(Color.DARK_GRAY);
				resultadoImc.setFont(new Font("Arial", Font.PLAIN, 18));
				resultadoImc.setForeground(Color.WHITE);
				resultadoMetabolismo.setFont(new Font("Arial", Font.PLAIN, 18));
				resultadoMetabolismo.setForeground(Color.WHITE);
				
				mensagemErro.setFont(new Font("Arial", Font.PLAIN, 18));
				mensagemErro.setForeground(Color.YELLOW);
				
				separatorFinal.setForeground(Color.LIGHT_GRAY);
				separatorFinal.setBackground(Color.LIGHT_GRAY);
				painelFinal.setBackground(Color.DARK_GRAY);
				painelIdentificacao.setBackground(Color.DARK_GRAY);


				labelUniversidade.setForeground(Color.WHITE);
				labelUniversidade.setFont(new Font("Arial", Font.PLAIN, 12));

				labelDisciplina.setForeground(Color.WHITE);
				labelDisciplina.setFont(new Font("Arial", Font.PLAIN, 12));

				labelProfessor.setForeground(Color.WHITE);
				labelProfessor.setFont(new Font("Arial", Font.PLAIN, 12));

				labelAluno.setForeground(Color.WHITE);
				labelAluno.setFont(new Font("Arial", Font.PLAIN, 12));
			}
		});
		
		temaClaro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				contentPane.setBackground(Color.LIGHT_GRAY);
		
				painelNome.setBackground(Color.WHITE);
				labelNome.setForeground(Color.BLACK);
				labelNome.setFont(new Font("Arial", Font.PLAIN, 18));
				textFieldNome.setBackground(Color.WHITE);
				textFieldNome.setFont(new Font("Arial", Font.PLAIN, 16));
				
				painelPeso.setBackground(Color.WHITE);
				labelPeso.setForeground(Color.BLACK);
				labelPeso.setFont(new Font("Arial", Font.PLAIN, 18));
				textFieldPeso.setBackground(Color.WHITE);
				textFieldPeso.setFont(new Font("Arial", Font.PLAIN, 18));
				
				painelAltura.setBackground(Color.WHITE);
				labelAltura.setForeground(Color.BLACK);
				labelAltura.setFont(new Font("Arial", Font.PLAIN, 18));
				textFieldAltura.setBackground(Color.WHITE);
				textFieldAltura.setFont(new Font("Arial", Font.PLAIN, 18));
				
				painelIdade.setBackground(Color.WHITE);
				labelIdade.setForeground(Color.BLACK);
				labelIdade.setFont(new Font("Arial", Font.PLAIN, 18));
				textFieldIdade.setBackground(Color.WHITE);
				textFieldIdade.setFont(new Font("Arial", Font.PLAIN, 18));
				
				labelCamposObrigatorios.setForeground(Color.RED);
				labelCamposObrigatorios.setFont(new Font("Arial", Font.BOLD, 13));
				
				painelSexo.setBackground(Color.WHITE);
				labelSexo.setForeground(Color.BLACK);
				labelSexo.setFont(new Font("Arial", Font.PLAIN, 18));
				
				painelBotoes.setBackground(Color.WHITE);
				radioButtonMasculino.setForeground(Color.BLACK);
				radioButtonMasculino.setBackground(Color.WHITE);
				radioButtonFeminino.setForeground(Color.BLACK);
				radioButtonFeminino.setBackground(Color.WHITE);
				
				painelResultado.setBackground(Color.WHITE);
				labelResultados.setFont(new Font("Arial", Font.BOLD, 20));
				labelResultados.setForeground(Color.BLACK);
				separatorResultado.setBackground(Color.WHITE);
				separatorResultado.setForeground(Color.WHITE);
				resultadoImc.setFont(new Font("Arial", Font.PLAIN, 18));
				resultadoImc.setForeground(Color.BLACK);
				resultadoMetabolismo.setFont(new Font("Arial", Font.PLAIN, 18));
				resultadoMetabolismo.setForeground(Color.BLACK);
				
				mensagemErro.setFont(new Font("Arial", Font.PLAIN, 18));
				mensagemErro.setForeground(Color.RED);
				
				separatorFinal.setForeground(Color.LIGHT_GRAY);
				separatorFinal.setBackground(Color.LIGHT_GRAY);
				painelFinal.setBackground(Color.WHITE);
				painelIdentificacao.setBackground(Color.WHITE);


				labelUniversidade.setForeground(Color.BLACK);
				labelUniversidade.setFont(new Font("Arial", Font.PLAIN, 12));

				labelDisciplina.setForeground(Color.BLACK);
				labelDisciplina.setFont(new Font("Arial", Font.PLAIN, 12));

				labelProfessor.setForeground(Color.BLACK);
				labelProfessor.setFont(new Font("Arial", Font.PLAIN, 12));

				labelAluno.setForeground(Color.BLACK);
				labelAluno.setFont(new Font("Arial", Font.PLAIN, 12));
								
			}
		});
		
		ButtonGroup buttonGroupTema = new ButtonGroup();
		buttonGroupTema.add(temaEscuro);
		buttonGroupTema.add(temaClaro);
		
		menuTema.add(temaEscuro);
		menuTema.add(temaClaro);
		
		setContentPane(contentPane);
	}

	private static void mostrarSplash() {
		// Criando a tela de carregamento
		JWindow window = new JWindow();
		ImageIcon imageIcon = new ImageIcon("https://th.bing.com/th/id/OIG1.tlt2y26ulvKjSorWphm_?pid=ImgGn");
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
		double imc = peso / Math.pow(altura/100, 2);
		return imc;
	}

	private static String resultadoImc(double imc) {
		String imcFormatado = String.format("%.2f", imc);
		if(imc < 18.5) {
			return "IMC: " + imcFormatado + " - Abaixo do peso: IMC menor que 18,5";
		} else if (imc >= 18.5 && imc <= 24.9) {
			return "IMC: " + imcFormatado + " - Peso normal: IMC entre 18,5 e 24,9";
		} else if (imc > 25 && imc <= 29.9) {
			return "IMC: " + imcFormatado + " - Obesidade grau I: IMC entre 30 e 34,9";
		} else if (imc > 30 && imc <= 34.9) {
			return "IMC: " + imcFormatado + " - Obesidade grau I: IMC entre 30 e 34,9";		
		} else if (imc > 35 && imc <= 39.9) {
			return "IMC: " + imcFormatado + " - Obesidade grau II (severa): IMC entre 35 e 39,9";
		} else {
			return "IMC: " + imcFormatado + " - Obesidade grau III (mórbida): IMC maior que 40";
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

package com.example.atividadessobreincrementao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atividadessobreincrementao.ui.theme.AtividadesSobreIncrementaçãoTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtividadesSobreIncrementaçãoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Coloquei um Scroll para você conseguir ver todos os exercícios na mesma tela
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        AtividadesBásicas()
                        Condicao()
                        Entrada()
                        Derivado()
                        ParOuImpar() // Exercício 9
                        LoginSimples() // Exercício 10
                        DesafioTema() // Desafio Final
                    }
                }
            }
        }
    }
}

// --- SEUS COMPOSABLES ORIGINAIS (COM AJUSTES DE LAYOUT) ---

@Composable
fun AtividadesBásicas() {
    var valor by remember { mutableStateOf(0) }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Exercício: Contador", fontWeight = FontWeight.Bold)
            Text(text = valor.toString(), fontSize = 24.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { valor++ }) { Text("+") }
                Button(onClick = { valor-- }) { Text("-") }
                Button(onClick = { valor = 0 }) { Text("Reset") }
            }
        }
    }
}

@Composable
fun Condicao() {
    var mostrarTexto by remember { mutableStateOf(false) }
    var textoColorido by remember { mutableStateOf(true) }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Exercício: Condicionais", fontWeight = FontWeight.Bold)
            if (mostrarTexto) Text(text = "Olá bem-vindo ao Kotlin")
            Button(onClick = { mostrarTexto = !mostrarTexto }) {
                Text(if (mostrarTexto) "Esconder" else "Mostrar")
            }
            Text(
                text = "Olá Kotlin",
                color = if (textoColorido) Color.Blue else Color.Red
            )
            Button(onClick = { textoColorido = !textoColorido }) {
                Text("Mudar Cor")
            }
        }
    }
}

@Composable
fun Entrada() {
    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Exercício: Entrada de Dados", fontWeight = FontWeight.Bold)
            Text(text = "Olá $nome")
            TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
            TextField(value = idade, onValueChange = { idade = it }, label = { Text("Idade") })

            val idadeInt = idade.toIntOrNull() ?: 0
            Text(text = if (idadeInt >= 18) "Maior de idade" else "Menor de idade")
        }
    }
}

@Composable
fun Derivado() {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var soma by remember { mutableStateOf(0) }
    var valorContador by remember { mutableStateOf(0) }

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Exercício: Calculadora e Trava", fontWeight = FontWeight.Bold)
            TextField(value = numero1, onValueChange = { numero1 = it }, label = { Text("N1") })
            TextField(value = numero2, onValueChange = { numero2 = it }, label = { Text("N2") })
            Button(onClick = {
                val n1 = numero1.toIntOrNull() ?: 0
                val n2 = numero2.toIntOrNull() ?: 0
                soma = n1 + n2
            }) { Text("Somar") }
            Text("Resultado: $soma")

            Spacer(Modifier.height(10.dp))
            Text("Contador (Limite 10): $valorContador")
            Row {
                Button(onClick = { valorContador++ }, enabled = valorContador < 10) { Text("Sobe") }
                Button(onClick = { valorContador-- }) { Text("Desce") }
            }
        }
    }
}

// --- NOVOS COMPOSABLES (NÍVEL 5 E DESAFIO) ---

@Composable
fun ParOuImpar() {
    var numero by remember { mutableStateOf(0) }
    var resultado by remember { mutableStateOf("-") }

    Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("🧩 Exercício 9: Par ou Ímpar", fontWeight = FontWeight.Bold)
            Text(text = "Número: $numero", fontSize = 28.sp)
            Text(text = "Classificação: $resultado", fontSize = 20.sp, color = Color.DarkGray)

            Button(onClick = {
                val novoNumero = Random.nextInt(1, 101)
                numero = novoNumero
                resultado = if (novoNumero % 2 == 0) "PAR" else "ÍMPAR"
            }) {
                Text("Gerar Número")
            }
        }
    }
}

@Composable
fun LoginSimples() {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mensagemErro by remember { mutableStateOf("") }

    Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("🧩 Exercício 10: Login", fontWeight = FontWeight.Bold)
            TextField(value = usuario, onValueChange = { usuario = it }, label = { Text("Usuário") })
            TextField(value = senha, onValueChange = { senha = it }, label = { Text("Senha") })

            Button(onClick = {
                mensagemErro = if (usuario == "admin" && senha == "1234") {
                    "✅ Login realizado!"
                } else {
                    "❌ Usuário ou senha inválidos"
                }
            }) {
                Text("Entrar")
            }
            if (mensagemErro.isNotEmpty()) {
                Text(text = mensagemErro, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}

@Composable
fun DesafioTema() {
    var isDarkTheme by remember { mutableStateOf(false) }

    // Definindo as cores com base no estado
    val backgroundColor = if (isDarkTheme) Color(0xFF212121) else Color(0xFFF5F5F5)
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val buttonColor = if (isDarkTheme) Color.DarkGray else Color.LightGray

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = if (isDarkTheme) "Modo Escuro Ativo" else "Modo Claro Ativo",
                color = textColor,
                fontSize = 18.sp
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { isDarkTheme = !isDarkTheme },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(
                    text = "Alternar Tema",
                    color = if (isDarkTheme) Color.White else Color.Black
                )
            }
        }
    }
}
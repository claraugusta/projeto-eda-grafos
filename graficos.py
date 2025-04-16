import pandas as pd
import matplotlib.pyplot as plt
import os
import re
import numpy as np

# Configurações
csv_path = "projeto/data/benchmark_Shortest_path.csv"
output_folder = "graficos"
os.makedirs(output_folder, exist_ok=True)

# Leitura e preparação dos dados
df = pd.read_csv(csv_path, decimal=',')
df.columns = [col.strip() for col in df.columns]
df['Tempo(ms)'] = pd.to_numeric(df['Tempo(ms)'], errors='coerce')
df['Memoria(KB)'] = pd.to_numeric(df['Memoria(KB)'], errors='coerce')
df = df.dropna(subset=['Tempo(ms)', 'Memoria(KB)'])

# Paleta de cores para todos os algoritmos possíveis (para consistência)
all_algorithms = df['Algoritmo'].unique()
colors = plt.cm.tab20(np.linspace(0, 1, len(all_algorithms)))
color_map = dict(zip(all_algorithms, colors))

for titulo, grupo_titulo in df.groupby("Titulo"):
    # Preparar nome base do arquivo
    nome_base = re.sub(r'[\\/*?:"<>|]', "", titulo)
    nome_base = nome_base.replace("\n", "_").replace(" ", "_")

    # Algoritmos presentes neste grupo específico
    algoritmos_no_grupo = grupo_titulo['Algoritmo'].unique()
    cenarios = grupo_titulo['Cenario'].unique()
    x = np.arange(len(cenarios))
    width = 0.8 / len(algoritmos_no_grupo)

    # ---------------- Gráfico de TEMPO ----------------
    fig, ax1 = plt.subplots(figsize=(12, 6))
    for i, algo in enumerate(algoritmos_no_grupo):
        dados_algo = grupo_titulo[grupo_titulo['Algoritmo'] == algo]
        valores = [dados_algo[dados_algo['Cenario'] == c]['Tempo(ms)'].mean() for c in cenarios]
        barras = ax1.bar(x + i * width, valores, width, color=color_map[algo], label=algo)
        for bar, valor in zip(barras, valores):
            height = bar.get_height()
            ax1.text(bar.get_x() + bar.get_width()/2., height, f'{valor:.3f}', ha='center', va='bottom', fontsize=8)

    ax1.set_title(f"{titulo}\nTempo de Execução (ms)", pad=20)
    ax1.set_ylabel("Tempo (ms)")
    ax1.set_xticks(x + width*(len(algoritmos_no_grupo)-1)/2)
    ax1.set_xticklabels(cenarios, rotation=45, ha='right')
    ax1.grid(axis='y', linestyle='--', alpha=0.7)
    ax1.legend(title="Algoritmos", fontsize=9)

    plt.tight_layout()
    plt.savefig(os.path.join(output_folder, f"{nome_base}_tempo.png"), bbox_inches='tight', dpi=150)
    plt.close()

    # ---------------- Gráfico de MEMÓRIA ----------------
    fig, ax2 = plt.subplots(figsize=(12, 6))
    for i, algo in enumerate(algoritmos_no_grupo):
        dados_algo = grupo_titulo[grupo_titulo['Algoritmo'] == algo]
        valores = [dados_algo[dados_algo['Cenario'] == c]['Memoria(KB)'].mean() for c in cenarios]
        barras = ax2.bar(x + i * width, valores, width, color=color_map[algo], label=algo)
        for bar, valor in zip(barras, valores):
            height = bar.get_height()
            ax2.text(bar.get_x() + bar.get_width()/2., height, f'{valor:.1f}', ha='center', va='bottom', fontsize=8)

    ax2.set_title(f"{titulo}\nUso de Memória (KB)", pad=20)
    ax2.set_ylabel("Memória (KB)")
    ax2.set_xticks(x + width*(len(algoritmos_no_grupo)-1)/2)
    ax2.set_xticklabels(cenarios, rotation=45, ha='right')
    ax2.grid(axis='y', linestyle='--', alpha=0.7)
    ax2.legend(title="Algoritmos", fontsize=9)

    plt.tight_layout()
    plt.savefig(os.path.join(output_folder, f"{nome_base}_memoria.png"), bbox_inches='tight', dpi=150)
    plt.close()

print(f"✅ Gráficos salvos separadamente em: {os.path.abspath(output_folder)}")

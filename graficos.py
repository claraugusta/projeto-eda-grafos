import pandas as pd
import matplotlib.pyplot as plt
import os
import re
import numpy as np

csv_path = "projeto/data/benchmark_Shortest_path.csv"
output_folder = "graficos"
os.makedirs(output_folder, exist_ok=True)

df = pd.read_csv(csv_path, decimal=',')
df.columns = [col.strip() for col in df.columns]
df['Tempo(ms)'] = pd.to_numeric(df['Tempo(ms)'], errors='coerce')
df['Memoria(KB)'] = pd.to_numeric(df['Memoria(KB)'], errors='coerce')
df = df.dropna(subset=['Tempo(ms)', 'Memoria(KB)'])


algoritmos = df['Algoritmo'].unique()
cores = plt.cm.tab20(np.linspace(0, 1, len(algoritmos)))  # Paleta com 20 cores
algoritmo_cores = dict(zip(algoritmos, cores))

for (titulo, cenario), grupo in df.groupby(["Titulo", "Cenario"]):
    nome_arquivo = re.sub(r'[\\/*?:"<>|]', "", f"{titulo}_{cenario}")
    nome_arquivo = nome_arquivo.replace("\n", "_").replace(" ", "_")
    fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(18, 6))  # Aumentei a largura para caber a legenda
    fig.suptitle(f"{titulo}\n{cenario}", fontsize=14, y=1.05)

    # Gráfico de TEMPO
    for algo in grupo['Algoritmo'].unique():
        dados_algo = grupo[grupo['Algoritmo'] == algo]
        ax1.bar(dados_algo['Algoritmo'], dados_algo['Tempo(ms)'], 
                color=algoritmo_cores[algo], label=algo)
    
    ax1.set_title("Tempo de Execução (ms)")
    ax1.set_ylabel("Tempo (ms)")
    ax1.tick_params(axis='x', rotation=45)
    
    for bar in ax1.containers:
        ax1.bar_label(bar, fmt='%.3f', padding=3, fontsize=9)

    # Gráfico de MEMÓRIA
    for algo in grupo['Algoritmo'].unique():
        dados_algo = grupo[grupo['Algoritmo'] == algo]
        ax2.bar(dados_algo['Algoritmo'], dados_algo['Memoria(KB)'], 
                color=algoritmo_cores[algo], label=algo)
    
    ax2.set_title("Uso de Memória (KB)")
    ax2.set_ylabel("Memória (KB)")
    ax2.tick_params(axis='x', rotation=45)
    
    for bar in ax2.containers:
        ax2.bar_label(bar, fmt='%.1f', padding=3, fontsize=9)

    handles, labels = ax1.get_legend_handles_labels()
    fig.legend(handles, labels, loc='upper right', bbox_to_anchor=(0.99, 0.9), 
               ncol=1, fontsize=9)

    plt.tight_layout()
    plt.savefig(os.path.join(output_folder, f"{nome_arquivo}.png"), 
                bbox_inches='tight', dpi=300)
    plt.close()

print(f"Gráficos salvos em: {os.path.abspath(output_folder)}")
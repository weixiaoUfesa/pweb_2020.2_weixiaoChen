import pandas as pd

def processar_arquivo(nome_arquivo):
    df = pd.read_csv(nome_arquivo, sep=',', low_memory=False)
    
    df["DT_NOTIFIC"] = pd.to_datetime(df["DT_NOTIFIC"], errors="coerce")
    df = df[~df["DT_NOTIFIC"].isna()]
    
    df["SEMANA"] = df["DT_NOTIFIC"].dt.isocalendar().week
    df["ANO"] = df["DT_NOTIFIC"].dt.isocalendar().year

    casos_gerais = (
        df.groupby(["ANO", "SEMANA"])
          .size()
          .reset_index(name="Total_casos")
    )

    df_hosp = df[df["HOSPITALIZ"] == 1]
    hosp_por_semana = (
        df_hosp.groupby(["ANO", "SEMANA"])
               .size()
               .reset_index(name="Total_hospitalizados")
    )

    df_obito = df[df["EVOLUCAO"] == 2]
    obito_por_semana = (
        df_obito.groupby(["ANO", "SEMANA"])
                .size()
                .reset_index(name="Total_obitos")
    )

    resumo = casos_gerais.merge(hosp_por_semana, on=["ANO", "SEMANA"], how="outer")
    resumo = resumo.merge(obito_por_semana, on=["ANO", "SEMANA"], how="outer")
    resumo = resumo.fillna(0)

    resumo[["Total_casos", "Total_hospitalizados", "Total_obitos"]] = resumo[[ 
        "Total_casos", "Total_hospitalizados", "Total_obitos"
    ]].astype(int)

    return resumo

# Loop nos arquivos
lista_resumos = []

for ano in range(24, 26):  # De 2014 (DENGBR14) a 2023 (DENGBR23)
    nome_arquivo =  f'E:/dengue/dengue_dados/DENGBR{ano}.csv'
    print(f"Lendo arquivo: {nome_arquivo}")
    resumo_ano = processar_arquivo(nome_arquivo)
    lista_resumos.append(resumo_ano)

# Concatenação e ordenação
resumo_todos_anos = pd.concat(lista_resumos)
resumo_final = resumo_todos_anos.groupby(["ANO", "SEMANA"]).sum().reset_index()
resumo_final = resumo_final[resumo_final["ANO"].between(2024, 2025)]
resumo_final = resumo_final.sort_values(["ANO", "SEMANA"])

# Exportar CSV
resumo_final.to_csv('resumo_dengue_2024_2025.csv', index=False)

print("✅ Arquivo 'resumo_dengue_2024_2025.csv' salvo com sucesso!")

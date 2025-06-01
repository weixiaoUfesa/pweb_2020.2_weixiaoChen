import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Carregar dados
df = pd.read_csv('sinan//DENGBR23.csv', sep=',')

# Converter coluna de data, ignorando erros
df["DT_NOTIFIC"] = pd.to_datetime(df["DT_NOTIFIC"], errors="coerce")

# Eliminar linhas com datas inválidas (NaT)
df = df[~df["DT_NOTIFIC"].isna()]

# Criar colunas de semana e ano baseadas em DT_NOTIFIC
df["SEMANA"] = df["DT_NOTIFIC"].dt.isocalendar().week
df["ANO"] = df["DT_NOTIFIC"].dt.isocalendar().year

# ----------- CASOS GERAIS --------------
casos_gerais = (
    df.groupby(["ANO", "SEMANA"])
      .size()
      .reset_index(name="Total_casos")
)

# ----------- HOSPITALIZADOS --------------
df_hosp = df[df["HOSPITALIZ"] == 1]
hosp_por_semana = (
    df_hosp.groupby(["ANO", "SEMANA"])
           .size()
           .reset_index(name="Total_hospitalizados")
)

# ----------- ÓBITOS --------------
df_obito = df[df["EVOLUCAO"] == 2]
obito_por_semana = (
    df_obito.groupby(["ANO", "SEMANA"])
            .size()
            .reset_index(name="Total_obitos")
)

# ----------- JUNTAR TUDO --------------
resumo = casos_gerais.merge(hosp_por_semana, on=["ANO", "SEMANA"], how="outer")
resumo = resumo.merge(obito_por_semana, on=["ANO", "SEMANA"], how="outer")

# Preencher valores ausentes com zero
resumo = resumo.fillna(0)

# Converter colunas para int
resumo[["Total_casos", "Total_hospitalizados", "Total_obitos"]] = resumo[[
    "Total_casos", "Total_hospitalizados", "Total_obitos"
]].astype(int)

# Ordenar por ano e semana
resumo = resumo.sort_values(["ANO", "SEMANA"])

# Mostrar resultado
print(resumo)

# Soma total dos casos agrupados por semana (ignorando o ano)
casos_gerais_por_semana = df.groupby("SEMANA").size().reset_index(name="Total_casos")
soma_total_casos = casos_gerais_por_semana["Total_casos"].sum()
print("Soma dos casos agrupados por semana:", soma_total_casos)

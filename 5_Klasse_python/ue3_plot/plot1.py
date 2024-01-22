import matplotlib.pyplot as plt
import math


PI = math.pi # für besonders Tippfaule ;-)
CNT = 1024
intervall = (math.pi*2 / 1024)

X = [ -PI + i * intervall for i in range(CNT) ] # CNT Werte von -pi ... +pi
C = [math.cos(x) for x in X] # CNT cosinuswerte für x von -pi ... +pi
S = [math.sin(x) for x in X]

plt.figure(figsize=(10,6), dpi=80)
plt.plot(X, C, color="blue", linewidth=2.5, linestyle="-")
plt.plot(X, S, color="red", linewidth=2.5, linestyle="-")

plt.savefig("plot1_hodina.png",dpi=72)
plt.show() # Anzeigen: Danach kann man die Grafik nicht mehr ändern!







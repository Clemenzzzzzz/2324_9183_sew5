import matplotlib.pyplot as plt
import math

PI = math.pi
CNT = 1024
intervall = (math.pi * 2 / 1024)

X = [-PI + i * intervall for i in range(CNT)]
C = [math.cos(x) for x in X]
S = [math.sin(x) for x in X]

plt.figure(figsize=(10, 6), dpi=80)

plt.xlim(min(X) * 1.1, max(X) * 1.1)
plt.ylim(min(C) * 1.1, max(C) * 1.1)

plt.xticks([-PI, -PI / 2, 0, PI / 2, PI],
           [r'$-\pi$', r'$-\pi/2$', r'$0$', r'$+\pi/2$', r'$+\pi$'])
plt.yticks([-1, 0, +1],
           [r'$-1$', r'$0$', r'$+1$'])

plt.plot(X, S, color="orange", linewidth=2.5, linestyle=":", label="sine")
plt.plot(X, C, color="green", linewidth=2.5, linestyle="--", label="cos")
plt.legend(loc='upper left', frameon=False)

ax = plt.gca()
ax.spines['right'].set_color('none')
ax.spines['top'].set_color('none')
ax.xaxis.set_ticks_position('bottom')
ax.spines['bottom'].set_position(('data', 0))
ax.yaxis.set_ticks_position('left')
ax.spines['left'].set_position(('data', 0))


angle = -45
angle_rad = math.radians(angle)
plt.plot([angle_rad, angle_rad], [0, math.cos(angle_rad)], color='red', linewidth=2.5, linestyle="--")
plt.scatter([angle_rad, ], [math.cos(angle_rad), ], 50, color='red')
plt.annotate(fr'$\cos({angle}^\circ)$',
             xy=(angle_rad, math.cos(angle_rad)), xycoords='data',
             xytext=(-90, -50), textcoords='offset points', fontsize=16,
             arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.2"))

t = 2 * PI / 3
plt.plot([t, t], [0, math.cos(t)], color='green', linewidth=2.5, linestyle="--")
plt.scatter([t, ], [math.cos(t), ], 50, color='green')
plt.annotate(r'$\sin(\frac{2\pi}{3})=\frac{\sqrt{3}}{2}$',
             xy=(t, math.sin(t)), xycoords='data',
             xytext=(+10, +30), textcoords='offset points', fontsize=16,
             arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.2"))
plt.plot([t, t], [0, math.sin(t)], color='orange', linewidth=2.5, linestyle="--")
plt.scatter([t, ], [math.sin(t), ], 50, color='orange')
plt.annotate(r'$\cos(\frac{2\pi}{3})=-\frac{1}{2}$',
             xy=(t, math.cos(t)), xycoords='data',
             xytext=(-90, -50), textcoords='offset points', fontsize=16,
             arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.2"))

for label in ax.get_xticklabels() + ax.get_yticklabels():
    label.set_fontsize(16)
    label.set_bbox(dict(facecolor='white', edgecolor='None', alpha=0.65))

# bei neueren matplot versionen
ax.set_axisbelow(True)

plt.title("Hodinas Funktionen", fontsize=18)


plt.savefig("plot1_hodina.png", dpi=72)
plt.show()







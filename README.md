

# qed.

static. compiled. weak types. secure. qbit-oriented. C-style. low-level. no Python. no wrappers. no runtime. just you and the machine.



`.qed` — a new language for quantum programming.
built for control. built for speed.
deterministic by default.
native quantum constructs.
no abstraction layers.

think like C. execute like hardware.
because quantum deserves better than scripts.

---

### why qed?

- **deterministic**: no interpreters. no guesswork. what you write is what you run.
- **secure**: static types. strict checks. zero ambiguity.
- **compiled**: turns code into quantum assembly. fast and portable.
- **qbit-native**: qubits are first-class. not an API,library or wrapper.
- **familiar**: C-style syntax. low-level control. built for systems thinkers.

---

### key features

- weak but static typing
- compiled to QASM-like intermediate format
- quantum and classical memory model
- deterministic simulation
- native gate operations: `H`, `CX`, `U3`, etc.
- compile-time optimizations for gate reduction

---

### example

```qed
qbit q[2];      // declare 2-qubit quantum register
H(q[0]);        // apply Hadamard gate
CX(q[0], q[1]); // controlled-not gate
measure q;      // classical measurement


```
### usage guide

in your terminal, run 

```bash
javac /com/parser/qed/*.java
```
then after its compiled, run

```bash
java com.parser.qed.qed.java

```

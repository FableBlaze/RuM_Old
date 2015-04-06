# RuM
RuM - Rule Mining platform

Currently in early development.


Main goals of the platform:

1. Web-based alternative to current popular process mining tools – No such platform exists based on our current knowledge.
2. RuM does not include process mining capabilities – Instead it will act as a “back-bone” for the mining process.
3. Process mining will be done by plugins – All parts of the process mining workflow will be implemented as plugins with each plugin handling a specific part of the workflow.
4. User can select the plugins for each mining task – Ideally every algorithm would be usable with any input, output and visualization plugin.
5. Access to plugins can be modified based on users or groups – In some cases it may be needed to allow users to access only specific plugins, especially if a high number of or some experimental plugins are installed.
6. Process mining is performed server-side on a “fire-and-forget” principle – The user may close the session after the mining task has been successfully started. RuM will carry out the task on its own. User can check the task during the next session.
7. Data is handled on a “per-account” basis – All data on RuM will be owned by some user account. 

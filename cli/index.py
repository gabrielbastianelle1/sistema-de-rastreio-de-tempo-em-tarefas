from textual.app import App, ComposeResult
from textual.containers import Container, Horizontal
from textual.widgets import Button, Input, Label



class ExampleApp(App):
    CSS_PATH = "test.tcss"
    TITLE = "Task Manager"

    def compose(self) -> ComposeResult:
        yield Label("Signin")
        yield Input("Digite algo")
        yield Button("Send", variant="error")
        yield Button("Signup", variant="warning")
        yield Button("Signup", variant="primary")
        yield Button("Signup", variant="default")


if __name__ == "__main__":
    app = ExampleApp()
    app.run()

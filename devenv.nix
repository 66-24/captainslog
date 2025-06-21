{ pkgs, lib, config, inputs, ... }:

{
  # ❗ Avoid referencing `config.env.APP_ENV` directly in the same config block:
  # This causes infinite recursion because `config` is still being built.
  # Instead, compute APP_ENV from `builtins.getEnv "APP_ENV"` or bind it earlier in a `let`.

  dotenv = {
    enable = true;
    # https://devenv.sh/integrations/dotenv/
    # https://devenv.sh/reference/options/#dotenvfilename
    filename = [".env.development"];
  };

  # https://devenv.sh/basics/
  env.GREET = "devenv";

  # https://devenv.sh/packages/
  packages = with pkgs; [ corretto21 ];

  # https://devenv.sh/languages/
  # languages.rust.enable = true;

  # https://devenv.sh/processes/
  # processes.cargo-watch.exec = "cargo-watch";

  # https://devenv.sh/services/
  # services.postgres.enable = true;

  # https://devenv.sh/scripts/
  scripts.hello.exec = ''
    echo "app env: $APP_ENV"
    echo "active spring profile: $SPRING_PROFILES_ACTIVE"
    echo hello from $GREET
  '';

  enterShell = ''
    hello
    git --version
  '';

  # https://devenv.sh/tasks/
  # tasks = {
  #   "myproj:setup".exec = "mytool build";
  #   "devenv:enterShell".after = [ "myproj:setup" ];
  # };

  # https://devenv.sh/tests/
  enterTest = ''
    echo "Running tests"
    git --version | grep --color=auto "${pkgs.git.version}"
  '';

  # https://devenv.sh/git-hooks/
  # git-hooks.hooks.shellcheck.enable = true;

  # See full reference at https://devenv.sh/reference/options/
}
